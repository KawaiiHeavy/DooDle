import { Component, Inject, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { httpInterceptorProviders } from 'src/app/auth/auth-interceptor';
import { Question } from 'src/app/models/question.model';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';
import { TestService } from 'src/app/services/test.service';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-test-creating',
  templateUrl: './test-creating.component.html',
  styleUrls: ['./test-creating.component.scss']
})
export class TestCreatingComponent implements OnInit {

  test: Test;
  countOfQuestions: number = 5;
  minValue: number = 1;
  maxValue: number = 10;
  step: number = 1;
  isSaved: boolean = false;

  @Input()
  creator: User;
  title: string = "";
  seconds: number = 600;
  questions: Question[] = [];

  // TODO: Временная затычка, переделать на инжект теста (возможно)
  constructor(private router: Router, 
    private testService: TestService) {
    this.test = history.state;
    //console.log(this.router.getCurrentNavigation().extras.state);
    console.log(this.test);
    if (this.test.creator) {
      this.creator = this.test.creator;
      this.title = this.test.title;
      this.seconds = this.test.seconds;
      this.countOfQuestions = this.test.questions.length;
      this.questions = this.test.questions;
    }
  }

  ngOnInit(): void {
  }

  onChange(){
    this.questions = [];
    for (let i = 0; i < this.countOfQuestions; i++){
      this.questions.push(new Question(
        "",
        0
      ));
    }
  }

  addUUIDToTestObjects(){
    let result : Question[] = JSON.parse(JSON.stringify(this.questions));
    console.log(result);
    result.forEach(question => {
      question.id = uuidv4();
      question.possibleAnswers.forEach(answer => {
        answer.id = uuidv4();
      })
    })
    return result;
  }

  saveTest(){
    this.isSaved = true;

    let test: Test;

    if (!this.test.id){
      test = new Test(
        uuidv4(),
        this.title,
        this.creator,
        null,
        this.addUUIDToTestObjects(),
        null,
        null,
        this.seconds
      );
    }
    else {
      test = new Test(
        this.test.id,
        this.title,
        this.creator,
        null,
        this.addUUIDToTestObjects(),
        null,
        null,
        this.seconds
      );
    }
    
    this.testService.saveTest(test).subscribe(data => console.log("Это работает"));
  }
}
