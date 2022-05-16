import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  roles: string[];
  authority: string;

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities().reverse();
      this.roles.every(role => {
        if (role === 'ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'TRAINER') {
          this.authority = 'trainer';
          return false;
        } else if (role === 'STUDENT') {
          this.authority = 'student';
          return false;
        } else {
          this.authority = 'user';
          return false;
        }
        return true;
      });
    }
  }
}
