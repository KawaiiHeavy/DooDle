import { Answer } from "./answer.model";

export class Question{
    id: string;
    questionText: string;
    scoreWeight: number;
    answers: Answer[];
}