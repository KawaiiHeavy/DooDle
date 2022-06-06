import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-answer-creating',
  templateUrl: './answer-creating.component.html',
  styleUrls: ['./answer-creating.component.scss']
})
export class AnswerCreatingComponent implements OnInit {

  answerText: string;
  correct: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  onChange(){}

}
