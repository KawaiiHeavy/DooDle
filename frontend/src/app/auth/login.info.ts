export class AuthLoginInfo {
    nickname: string;
    password: string;

    constructor(nickname: string, password: string) {
        this.nickname = nickname;
        this.password = password;
    }
}