export class SignupInfo { 
    nickname: string;
    email: string;
    roles: string[];
    password: string;

    constructor(nickname: string, email: string, password: string, roles: string[]){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}