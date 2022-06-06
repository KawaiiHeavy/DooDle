import { Component, OnInit } from '@angular/core';
import { Question } from 'src/app/models/question.model';

@Component({
  selector: 'app-test-creating',
  templateUrl: './test-creating.component.html',
  styleUrls: ['./test-creating.component.scss']
})
export class TestCreatingComponent implements OnInit {

  constructor() { }

  countOfQuestions = 5;
  minValue = 1;
  maxValue = 10;
  step = 1;

  questions: Question[] = [];

  ngOnInit(): void {
  }

  onChange(){
    this.questions = [];
    for (let i = 0; i < this.countOfQuestions; i++){
      this.questions.push(new Question());
    }
  }

  saveTest(){
    
  }
}
