import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { QuestionBlank } from 'src/app/models/questionBlank.model';
import { Result } from 'src/app/models/result.model';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';
import { TestService } from 'src/app/services/test.service';

@Component({
  selector: 'app-test-passing',
  templateUrl: './test-passing.component.html',
  styleUrls: ['./test-passing.component.scss']
})
export class TestPassingComponent implements OnInit {

  test: Test;
  questionBlanks: QuestionBlank[] = [];
  result: Result;

  user: User;

  constructor(private router: Router, 
    private activatedRoute:ActivatedRoute,
    private testService: TestService) { 
    
    console.log(this.router.getCurrentNavigation().extras.state);
  }


  ngOnInit(): void {
    this.test = history.state;
    console.log(this.test);
    for (let i = 0; i < this.test.questions.length; i++){
      console.log("Something");
      this.questionBlanks.push(new QuestionBlank(
        this.test.questions[i].id,
        this.test.questions[i].questionText,
        this.test.questions[i].scoreWeight,
        this.test.questions[i].answers,
        this.test.questions[i].answers));
    }
  }

  /*onChange(currentQuestion: Question, currentAnswer: Answer, isChecked: Event) {
    
    console.log("Here");
    // Переставление правильности ответа непосредственно у пользователя

    for (let i = 0; i < this.questionBlanks.length; i++){
      if (currentQuestion.id === this.questionBlanks[i].id){
        for (let j = 0; j < this.questionBlanks[i].userAnswers.length; j++){
          if (currentAnswer.id === this.questionBlanks[i].userAnswers[j].id){
            console.log(`Вопрос с текстом ${this.questionBlanks[i].questionText}, ответ ${this.questionBlanks[i].userAnswers[j].answerText} пользователь ранее отметил как ${this.questionBlanks[i].userAnswers[j].correct}`);
            this.questionBlanks[i].userAnswers[j].correct = event.checked;
            console.log(`Теперь же ${this.questionBlanks[i].userAnswers[j].answerText}`);
          }
        }
      }
    }
  }
  */
  
  onChange(){
    console.log("Something happened");
  }

  checkTest(){
    return this.testService.checkTest(this.questionBlanks).subscribe(data => {
      this.result = data;
    });
  }
}
