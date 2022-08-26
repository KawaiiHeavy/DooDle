import { AfterContentInit, Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';
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

  searchTitle: string = "";

  constructor(private userService: UserService, 
    private testService: TestService, 
    private tokenStorageService: TokenStorageService,
    private router: Router) { }


  ngOnInit(): void {
    this.userService.getUserByNickname(this.tokenStorageService.getNickname()).subscribe(data => this.user = data);
    this.userService.getUserByNickname(this.tokenStorageService.getNickname());
    this.getTests();
  }

  getUser(){
    this.userService.getUserByNickname(this.tokenStorageService.getNickname()).subscribe(data => this.user = data);
  }

  getTests(){
    this.testService.getAllTests().subscribe(data => {
      this.tests = data;
      console.log(this.tests);
    });
  }

  getUsers(): void {
    this.userService.getAllUsers().subscribe(user => console.log(user));
  }

  startTest(test: Test, user: User): void {
    console.log(test);
    console.log(`User with ${user.id} completing the test with ${test.id}`);
    this.router.navigateByUrl('testing/testPassing', {state: {test: test, user: user}});
  }

  getTestDetailInfo(test: Test): void {
    this.router.navigateByUrl('testing/testGettingDetailInfo', {state: test});
  }

  editTest(test: Test): void {
    this.router.navigateByUrl('testing/testCreating', { state : test });
  }
}
