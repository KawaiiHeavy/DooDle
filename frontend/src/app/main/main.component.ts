import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  nickname: string = '';
  password: string = '';
  email: string = '';

  constructor(public userService: UserService) { }

  ngOnInit(): void {
  }

  createUser(nickname: string, password: string, email: string){
    return this.userService.createUser(nickname, password, email).subscribe();
  }

  get(){
    return this.userService.getUsers().subscribe();
  }
  
}
