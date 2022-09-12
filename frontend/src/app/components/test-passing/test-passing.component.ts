import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, Subscription, timer } from 'rxjs';  
import { Result } from 'src/app/models/result.model';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';
import { QuestionService } from 'src/app/services/question.service';
import { TestService } from 'src/app/services/test.service';
import { UtilsTimerService } from 'src/app/services/utils.timer.service';

@Component({
  selector: 'app-test-passing',
  templateUrl: './test-passing.component.html',
  styleUrls: ['./test-passing.component.scss']
})
export class TestPassingComponent implements OnInit {

  test: Test;
  result: Result;
  user: User;
  testHasSent : boolean = false;

  timerSubscription: Subscription; 

  constructor(private router: Router, 
    private activatedRoute: ActivatedRoute,
    private testService: TestService,
    private timerService: UtilsTimerService,
    private questionService: QuestionService) { 
    
    console.log(this.router.getCurrentNavigation().extras.state);
  }

  ngOnInit(): void {
    
    this.timerService.pauseTimer();
    this.testService.getTestById(history.state.id).subscribe(test => { 
      this.test = test;
      this.test.questions.forEach(question => {
        
        const reader = new FileReader();
        reader.onload = () => {
          question.image = reader.result;
        }
        this.questionService.getImageFromQuestion(question.id)
        .subscribe(res => {
          console.log(res);
          question.image = 'data:image/jpeg;base64,' + res.picByte;
        });
        
        question.userAnswers = JSON.parse(JSON.stringify(question.answers));
        question.userAnswers.forEach(answer => {
          answer.correct = false;
        })
      });
      this.user = history.state.user;
  
      this.timerService.setTime(this.test.seconds);
    });
    this.timerService.startTimer();
    this.timerSubscription = timer(this.timerService.getTime() * 1000).pipe( 
      map(() => {
        this.checkTest(this.test);
      })
    ).subscribe();
  }

  checkTest(test: Test){
    let imageArray = [];
    test.questions.forEach(question => {
      imageArray.push(question.image);
      question.image = null;
    });
    return this.testService.checkTest(test).subscribe(data => {
      for (let i = 0; i < imageArray.length; ++i) {
        test.questions[i].image = imageArray[i];
      }
      this.testHasSent = true;
    });
  }

  getTime(){
    return this.timerService.getTime();
  }
}
