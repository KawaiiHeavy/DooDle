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
}