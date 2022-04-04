import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { SignupInfo } from '../../auth/signup-info';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  signupInfo: SignupInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  role: string = 'User';

  constructor(private authService: AuthService) { }

  ngOnInit() { }

  register() {
    console.log(this.form);

    this.signupInfo = new SignupInfo(
      this.form.username,
      this.form.email,
      this.form.password,
      this.form.roles);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  setTrainerRoles() {
    this.form.roles = ["TRAINER", "STUDENT", "USER"];
    this.role = 'Trainer';
  }

  setStudentRoles() {
    this.form.roles = ["STUDENT", "USER"];
    this.role = 'Student';
  }

  setUserRoles() {
    this.form.roles = ["USER"];
    this.role = 'User';
  }

}
