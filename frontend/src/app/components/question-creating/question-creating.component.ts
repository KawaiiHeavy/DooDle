import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';

@Component({
  selector: 'app-question-creating',
  templateUrl: './question-creating.component.html',
  styleUrls: ['./question-creating.component.scss']
})
export class QuestionCreatingComponent implements OnInit {

  @ViewChild('input') inputRef: ElementRef;
  form: FormGroup;
  imagePreview: any = '';

  @Input()
  question: Question;

  minValue = 2;
  maxValue = 6;
  countOfAnswers: number;

  constructor() { }

  ngOnInit(): void {
    this.countOfAnswers = 2;
    this.onChange();
  }

  onChange(){
    this.question.answers = [];
    for (let i = 0; i < this.countOfAnswers; i++){
      let answer : Answer = new Answer();
      answer.answerText = "";
      answer.correct = false;
      this.question.answers.push(answer);
    }
  }

  triggerClick() {
    this.inputRef.nativeElement.click();
  }

  onFileUpload(event: any) {
    const file = event.target.files[0];
    this.question.image = file;

    const reader = new FileReader();

    reader.onload = () => {
      this.imagePreview = reader.result;
    }

    reader.readAsDataURL(file);
  }
}
