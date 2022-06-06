import { Answer } from "./answer.model";

export class Question{
    id: string;
    questionText: string;
    scoreWeight: number;
    answers: Answer[];

    constructor(questionText: string, scoreWeight: number){
        this.questionText = questionText;
        this.scoreWeight = scoreWeight;
    };
}