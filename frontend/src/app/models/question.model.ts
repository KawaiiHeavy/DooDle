import { Answer } from "./answer.model";
import { Test } from "./test.model";

export class Question{
    id: string;
    questionText: string;
    scoreWeight: number;
    imageUrl: string;
    test: Test;
    answers: Answer[];
    userAnswers?: Answer[];

    constructor(){};
}