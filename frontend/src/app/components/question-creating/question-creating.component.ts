import { Component, Input, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';

@Component({
  selector: 'app-question-creating',
  templateUrl: './question-creating.component.html',
  styleUrls: ['./question-creating.component.scss']
})
export class QuestionCreatingComponent implements OnInit {

  @Input()
  question: Question;

  minValue = 2;
  maxValue = 6;
  countOfAnswers: number;

  constructor() { }

  ngOnInit(): void {
  }

  onChange(){
    this.question.possibleAnswers = [];
    for (let i = 0; i < this.countOfAnswers; i++){
      this.question.possibleAnswers.push(new Answer(
        "", false
      ));
    }
  }

}
