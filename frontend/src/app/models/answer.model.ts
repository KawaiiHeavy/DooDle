import { Question } from "./question.model";

export class Answer{
    id: string;
    question: Question;
    answerText: string;
    correct: boolean;

    constructor(){};
}