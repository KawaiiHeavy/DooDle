import { QuestionBlank } from "./questionBlank.model";
import { Result } from "./result.model";
import { Test } from "./test.model";
import { User } from "./user.model";

export class TestBlank{
    id: string;
    title: string;
    creator: User;
    members: User[];
    questionBlanks: QuestionBlank[];
    results: Result[];
    maxBall: number;
    seconds: number;
    participant: User;

    constructor(test: Test, participant: User, questionBlanks: QuestionBlank[]){
        this.id = test.id;
        this.title = test.title;
        this.creator = test.creator;
        this.members = test.members;
        this.questionBlanks = questionBlanks;
        this.results = test.results;
        this.maxBall = test.maxBall;
        this.seconds = test.seconds;
        this.participant = participant;
    }

    
}