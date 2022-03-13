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
        userRole: any,
        ownedTests: any){
            this.id = id;
            this.nickname = nickname;
            this.password = password;
            this.email = email;
            this.phone = phone;
            this.userRole = userRole;
            this.ownedTests = ownedTests;
        }

    id: string;
    nickname: string;
    password: string;
    email: string;
    phone: string;
    userRole: Role;
    ownedTests: string;
}