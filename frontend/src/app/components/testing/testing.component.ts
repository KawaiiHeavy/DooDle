import { AfterContentInit, Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';
import { PreparedDataService } from 'src/app/services/preparedData.service';
import { TestService } from 'src/app/services/test.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.scss']
})
export class TestingComponent implements OnInit {


  user: User;

  tests: Test[];

  constructor(private userService: UserService, 
    private testService: TestService, 
    private tokenStorageService: TokenStorageService,
    private preparedDataService: PreparedDataService,
    private router: Router) { }


  ngOnInit(): void {
    this.userService.getUserByNickname(this.tokenStorageService.getNickname()).subscribe(data => this.user = data);
    this.userService.getUserByNickname(this.tokenStorageService.getNickname());
    console.log(this.user);
    this.getTests();
  }

  getUser(){
    this.userService.getUserByNickname(this.tokenStorageService.getNickname()).subscribe(data => this.user = data);
  }

  getTests(){
    this.testService.getTests().subscribe(data => this.tests = data);
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(user => console.log(user));
  }

  startTest(test: Test, user: User){
    //console.log(this.user.id);
    //console.log(test.id);

    test.questions = this.preparedDataService.getPreparedQuestions2();
    console.log(`User with ${user.id} completing the test with ${test.id}`);
    this.router.navigateByUrl('testing/testPassing', {state: test});
  }
}
