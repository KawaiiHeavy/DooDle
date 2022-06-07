import { Answer } from "./answer.model";

export class QuestionBlank{
    id: string;
    questionText: string;
    scoreWeight: number;
    answers: Answer[];
    userAnswers: Answer[];
    imageUrl: string;

    constructor(id: string, questiontText: string, scoreWeight: number, answers: Answer[], userAnswers: Answer[], imageUrl: string){
        this.id = id;
        this.questionText = questiontText;
        this.scoreWeight = scoreWeight;
        this.answers = answers;
        this.userAnswers = userAnswers;
        this.imageUrl = imageUrl;
    }
}