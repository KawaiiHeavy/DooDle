import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from '../../services/user.service';

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
    let user: User = new User();
    user.nickname = nickname;
    user.password = password;
    user.email = email;
    return this.userService.addUser(user).subscribe();
  }

  get(){
    return this.userService.getAllUsers().subscribe();
  }
  
}
