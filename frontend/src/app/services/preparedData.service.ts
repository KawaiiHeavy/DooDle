import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from "@angular/core";
import { Answer } from "../models/answer.model";
import { Question } from "../models/question.model";
import { v4 as uuidv4 } from 'uuid';


@Injectable({
    providedIn: "root",
})
export class PreparedDataService implements OnInit {

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

    getPreparedQuestions(): Question[]{
        const answer1: Answer = {
            id: uuidv4(),
            answerText: "Для пакетов третьего уровня",
            question: null,
            correct: true
        }

        const answer2: Answer = {
            id: uuidv4(),
            answerText: "Для фреймов второго уровня",
            question: null,
            correct: false
        }

        const answer3: Answer = {
            id: uuidv4(),
            answerText: "Для дейтаграмм второго уровня",
            question: null,
            correct: false
        }

        const answer4: Answer = {
            id: uuidv4(),
            answerText: "Для битов первого уровня",
            question: null,
            correct: false
        }

        const answer5: Answer = {
            id: uuidv4(),
            answerText: "Для сегментов четвертого уровня",
            question: null,
            correct: true
        }

        var array1: Answer[] = [answer1, answer2, answer3, answer4, answer5];

        const question1: Question = {
            id: uuidv4(),
            questionText: "Для каких объектов маршрутизатор выбирает маршрут?",
            scoreWeight: 10,
            answers: array1
        }



        const answer6: Answer = {
            id: uuidv4(),
            answerText: "все устройства той локальной сети, которой принадлежит хост, пославший ARP запрос",
            question: null,
            correct: true
        }

        const answer7: Answer = {
            id: uuidv4(),
            answerText: "только компьютеры той локальной сети, которой принадлежит хост, пославший ARP запрос",
            question: null,
            correct: false
        }

        const answer8: Answer = {
            id: uuidv4(),
            answerText: "только порты коммутаторов той локальной сети, которой принадлежит хост, пославший ARP запрос",
            question: null,
            correct: false
        }

        const answer9: Answer = {
            id: uuidv4(),
            answerText: "Все устройства той локальной сети, которой принадлежит хост, пославший ARP запрос, и порты маршрутизаторов соседних сетей",
            question: null,
            correct: false
        }

        var array2: Answer[] = [answer6, answer7, answer8, answer9];

        const question2: Question = {
            id: uuidv4(),
            questionText: "Какие устройства сети получают ARP запрос (ARP Request)?",
            scoreWeight: 15,
            answers: array2
        }
        
        return [question1, question2];
    }
}