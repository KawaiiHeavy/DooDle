export class SignupInfo { 
    name: string;
    nickname: string;
    email: string;
    role: string[];
    password: string;

    constructor(name: string, nickname: string, email: string, password: string){
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = ['USER'];
    }
}