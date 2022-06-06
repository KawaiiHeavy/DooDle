import { Question } from "./question.model";
import { Result } from "./result.model";
import { User } from "./user.model";

export class Test{
    id: string;
    title: string;
    creator: User;
    members: User[];
    questions: Question[];
    results: Result[];
    maxBall: number;
    seconds: number;

    constructor(id: string,
        title: string,
        creator: User,
        members: User[],
        questions: Question[],
        results: Result[],
        maxBall: number,
        seconds: number){
            this.id = id;
            this.title = title;
            this.creator = creator;
            this.members = members;
            this.questions = questions;
            this.results = results;
            this.maxBall = maxBall;
            this.seconds = seconds;
        };

    
}