import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const NICKNAME_KEY = 'AuthNickname';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  private roles: Array<string> = [];
  constructor() { }

  signOut(){
    window.sessionStorage.clear();
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public getNickname(): string | null {
    return sessionStorage.getItem(NICKNAME_KEY);
  }

  public getAuthorities(): string[] {
    this.roles = [];

    if (sessionStorage.getItem(TOKEN_KEY)) {
      console.log('test');
      console.log(sessionStorage.getItem(AUTHORITIES_KEY));
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY) || '{}').forEach((authority: string) => {
        this.roles.push(authority);
      })
    }

    return this.roles;
  }

  public saveToken(token: string){
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public saveNickname(nickname: string){
    window.sessionStorage.removeItem(NICKNAME_KEY);
    window.sessionStorage.setItem(NICKNAME_KEY, nickname);
  }

  public saveAuthorities(authorities: string[]){
    console.log('saveAuthorities');
    console.log(authorities);
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

}
