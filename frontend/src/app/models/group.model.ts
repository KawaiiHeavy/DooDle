import { User } from "./user.model";

export class Group {

    id: string;
    title: string;
    groupLeader: User;
    members: User[];

}