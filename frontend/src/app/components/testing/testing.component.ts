import { Component, OnInit } from '@angular/core';
import { Test } from 'src/app/models/test.model';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.scss']
})
export class TestingComponent implements OnInit {

  user: User;
  tests: Test[];

  constructor() { }

  ngOnInit(): void {
  }

}
