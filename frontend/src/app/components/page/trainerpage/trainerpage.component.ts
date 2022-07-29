import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-trainerpage',
  templateUrl: './trainerpage.component.html',
  styleUrls: ['./trainerpage.component.scss']
})
export class TrainerpageComponent implements OnInit {

  constructor(private tokenStorageService: TokenStorageService, private userService: UserService) { }

  trainer: User;
  creatingTestShow : boolean = false;

  ngOnInit(): void {
    this.userService.getUserByNickname(this.tokenStorageService.getNickname()).subscribe(data => this.trainer = data);
  }

  changeTestCreatingVisibility(){
    console.log(this.creatingTestShow);
    this.creatingTestShow = !this.creatingTestShow;
  }
}
