import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login.info';
import { SignupInfo } from './signup-info';
import { environment } from "src/environments/environment";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = environment.apiBaseUrl + '/auth/signin';
  private signupUrl = environment.apiBaseUrl + '/auth/signup';

  constructor(private http: HttpClient) { }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignupInfo): Observable<JwtResponse>{
    return this.http.post<JwtResponse>(this.signupUrl, info, httpOptions);
  }
}
