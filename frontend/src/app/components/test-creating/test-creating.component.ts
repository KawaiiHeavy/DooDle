import { Component, Input, OnInit } from '@angular/core';
import { httpInterceptorProviders } from 'src/app/auth/auth-interceptor';
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

  constructor(private testService: TestService) { }

  countOfQuestions = 5;
  minValue = 1;
  maxValue = 10;
  step = 1;

  @Input()
  creator: User;
  title: string = "";
  seconds: number = 600;

  questions: Question[] = [];

  ngOnInit(): void {
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
      question.answers.forEach(answer => {
        answer.id = uuidv4();
      })
    })
    return result;
  }

  saveTest(){
    let test: Test = new Test(
      uuidv4(),
      this.title,
      this.creator,
      null,
      this.addUUIDToTestObjects(),
      null,
      null,
      this.seconds
    );
    
    this.testService.saveTest(test).subscribe(data => console.log("Это работает"));
  }
}
