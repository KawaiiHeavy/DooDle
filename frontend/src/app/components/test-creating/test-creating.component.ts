import { Component, Inject, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  }

  ngOnInit(): void {
    this.test = new Test();
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
    this.testService.addTest(this.test).subscribe(data => console.log("Это работает"));
  }
}
