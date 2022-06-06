import { Component, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';

@Component({
  selector: 'app-question-creating',
  templateUrl: './question-creating.component.html',
  styleUrls: ['./question-creating.component.scss']
})
export class QuestionCreatingComponent implements OnInit {

  minValue = 2;
  maxValue = 6;
  countOfAnswers: number;
  questionText: string;
  scoreForQuestion: number;
  answers: Answer[] = []

  constructor() { }

  ngOnInit(): void {
  }

  onChange(){
    this.answers = [];
    for (let i = 0; i < this.countOfAnswers; i++){
      this.answers.push(new Answer());
    }
  }

}
