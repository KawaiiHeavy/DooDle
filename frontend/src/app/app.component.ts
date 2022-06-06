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

    const potentialToken = localStorage.getItem('auth-token');
    if (potentialToken !== null) {
      this.tokenStorage.saveToken(potentialToken);
    }

    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();

      if (this.roles.includes("ADMIN")) {
        this.authority = "admin";
        return false;
      }

      if (this.roles.includes("TRAINER")) {
        this.authority = "trainer";
        return false;
      }

      if (this.roles.includes("STUDENT")) {
        this.authority = "student";
        return false;
      }

      this.authority = "user";
      return false;

    }

    return true;
  }
}
