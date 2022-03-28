export class JwtResponse {
    token: string;
    type: string;
    nickname: string;
    roles: string[];

    constructor(token: string, type: string, nickname: string, roles: string) {
        this.token = token;
        this.type = type;
        this.nickname = nickname;
        this.roles = ['user'];
    }
}