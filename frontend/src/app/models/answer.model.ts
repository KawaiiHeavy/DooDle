import { Question } from "./question.model";

export class Answer{
    id: string;
    question: Question;
    answerText: string;
    correct: boolean;

    constructor(answerText: string, correct: boolean){
        this.answerText = answerText;
        this.correct = correct;
    };
}