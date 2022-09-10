import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map, Subscription, timer } from 'rxjs';  
import { Result } from 'src/app/models/result.model';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';
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

  timerSubscription: Subscription; 

  constructor(private router: Router, 
    private activatedRoute: ActivatedRoute,
    private testService: TestService,
    private timerService: UtilsTimerService) { 
    
    console.log(this.router.getCurrentNavigation().extras.state);
  }

  ngOnInit(): void {
    
    this.timerService.pauseTimer();
    this.testService.getTestById(history.state.id).subscribe(test => { 
      this.test = test;
      this.test.questions.forEach(question => {
        question.userAnswers = JSON.parse(JSON.stringify(question.answers));
        question.scoreWeight = 1;
        question.userAnswers.forEach(answer => {
          answer.correct = false;
        })
      });
    });
    this.user = history.state.user;
  
    this.timerService.setTime(this.test.seconds);
    this.timerService.startTimer();

    this.timerSubscription = timer(this.timerService.getTime() * 1000).pipe( 
      map(() => {
        this.checkTest(this.test);
      })
    ).subscribe();
    
  }

  checkTest(test: Test){
    console.log(test);
    return this.testService.checkTest(test).subscribe(data => {
      console.log(data);
    });
  }

  getTime(){
    return this.timerService.getTime();
  }
}
