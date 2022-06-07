import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, timer } from 'rxjs';  
import { QuestionBlank } from 'src/app/models/questionBlank.model';
import { Result } from 'src/app/models/result.model';
import { Test } from 'src/app/models/test.model';
import { TestBlank } from 'src/app/models/testBlank.model';
import { User } from 'src/app/models/user.model';
import { TestService } from 'src/app/services/test.service';
import { UtilsTimerService } from 'src/app/services/utils.timer.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-test-passing',
  templateUrl: './test-passing.component.html',
  styleUrls: ['./test-passing.component.scss']
})
export class TestPassingComponent implements OnInit {

  test: Test;
  questionBlanks: QuestionBlank[] = [];
  result: Result;
  user: User;

  timerSubscription: Subscription; 

  constructor(private router: Router, 
    private activatedRoute: ActivatedRoute,
    private testService: TestService,
    private timerService: UtilsTimerService) { 
    
    console.log(this.router.getCurrentNavigation().extras.state);
  }

  ngOnInit(): void {
    
    this.timerService.pauseTimer();
    this.test = history.state.test;
    this.user = history.state.user;
    console.log(this.test);
    for (let i = 0; i < this.test.questions.length; i++){
      console.log("Something");
      this.questionBlanks.push(new QuestionBlank(
        this.test.questions[i].id,
        this.test.questions[i].questionText,
        this.test.questions[i].scoreWeight,
        JSON.parse(JSON.stringify(this.test.questions[i].possibleAnswers)),
        JSON.parse(JSON.stringify(this.test.questions[i].possibleAnswers)),
        this.test.questions[i].imageUrl
        ));
    }

    this.questionBlanks.forEach(questionBlank => {
      questionBlank.userAnswers.forEach(answer => {
        answer.correct = false;
      })
    })

    console.log(this.questionBlanks);

    this.timerService.setTime(this.test.seconds);
    this.timerService.startTimer();

    this.timerSubscription = timer(this.timerService.getTime() * 1000).pipe( 
      map(() => {
        this.checkTest();
      })
    ).subscribe(); 
  }

  checkTest(){
    let testBlank : TestBlank = new TestBlank(this.test, this.user, this.questionBlanks);

    return this.testService.checkTest(testBlank).subscribe(data => {
      this.result = data;
    });
  }

  getTime(){
    return this.timerService.getTime();
  }
}
