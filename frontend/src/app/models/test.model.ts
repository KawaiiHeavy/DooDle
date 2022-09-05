import { Question } from "./question.model";
import { Result } from "./result.model";
import { User } from "./user.model";

export class Test{
    id: string;
    title: string;
    creator: User;
    members: User[];
    results: Result[];
    questions: Question[];
    maxBall: number;
    seconds: number;
}