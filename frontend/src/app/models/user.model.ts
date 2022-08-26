export enum Role {
    ADMIN="ADMIN",
    STUDENT="STUDENT",
    TRAINER="TRAINER",
    USER="USER"
}

export class User{

    constructor(){}

    id: string;
    nickname: string;
    password: string;
    email: string;
    phone: string;
    roles: Role[];
    ownedTests: any[];
}