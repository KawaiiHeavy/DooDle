import { Component, Input, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';

@Component({
  selector: 'app-answer-creating',
  templateUrl: './answer-creating.component.html',
  styleUrls: ['./answer-creating.component.scss']
})
export class AnswerCreatingComponent implements OnInit {

  @Input()
  answer: Answer;

  constructor() { }

  ngOnInit(): void {
  }

  onChange(){}

}
