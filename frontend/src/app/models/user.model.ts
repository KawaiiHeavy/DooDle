export enum Role {
    ADMIN="ADMIN",
    STUDENT="STUDENT",
    TRAINER="TRAINER",
    USER="USER"
}

export class User{

    constructor(id: any,
        nickname: string,
        password: string,
        email: string,
        phone: any,
        roles: any,
        ownedTests: any[]){
            this.id = id;
            this.nickname = nickname;
            this.password = password;
            this.email = email;
            this.phone = phone;
            this.roles = roles;
            this.ownedTests = ownedTests;
        }

    id: string;
    nickname: string;
    password: string;
    email: string;
    phone: string;
    roles: Role[];
    ownedTests: any[];
}