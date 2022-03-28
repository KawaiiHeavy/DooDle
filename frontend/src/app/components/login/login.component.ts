import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { AuthLoginInfo } from 'src/app/auth/login.info';
import { TokenStorageService } from 'src/app/auth/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo?: AuthLoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()){
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit(){
    console.log('test this form');
    console.log(this.form);

    this.loginInfo = new AuthLoginInfo(
      this.form.nickname,
      this.form.password
    );

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.log('111111');
        console.log(data);
        console.log(data.token);
        console.log(data.nickname);
        console.log(data.roles);
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveNickname(data.nickname);
        this.tokenStorage.saveAuthorities(data.roles);
 
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.errorMessage;
        this.isLoginFailed = true;
      }
    )
  }

  reloadPage() {
    window.location.reload();
  }

}
