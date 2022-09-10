import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/question.model';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';
import { TestService } from 'src/app/services/test.service';

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

  constructor(private router: Router,
    private testService: TestService) {
  }

  ngOnInit(): void {
    this.test = new Test();
    this.test.questions = [];
  }

  onChange(){
    this.questions = [];
    for (let i = 0; i < this.countOfQuestions; i++){
      let question : Question = new Question();
      question.imageUrl = "";
      this.test.questions.push(question);
      question.scoreWeight = 0;
    }
  }

  saveTest(){
    this.isSaved = true;
    this.test.creator = this.creator;
    this.testService.addTest(this.test).subscribe(recvTest => { 
      console.log(`Test ${this.test.title} has sent`); 
    });
  }
}
