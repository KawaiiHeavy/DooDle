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
        JSON.parse(JSON.stringify(this.test.questions[i].answers)),
        JSON.parse(JSON.stringify(this.test.questions[i].answers))))
    }
  }

  
  onChange(){
    console.log("Something happened");
  }

  checkTest(){
    console.log(this.test.questions);
    console.log(this.questionBlanks);

    return this.testService.checkTest(this.questionBlanks).subscribe(data => {
      this.result = data;
    });
  }
}
