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

    getPreparedQuestions1(): Question[]{
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
        
        const answer10: Answer = {
            id: uuidv4(),
            answerText: "AES",
            question: null,
            correct: true
        }

        const answer11: Answer = {
            id: uuidv4(),
            answerText: "TKIP",
            question: null,
            correct: false
        }

        const answer12: Answer = {
            id: uuidv4(),
            answerText: "AUTO",
            question: null,
            correct: false
        }

        const answer13: Answer = {
            id: uuidv4(),
            answerText: "CCMP",
            question: null,
            correct: false
        }

        var array3: Answer[] = [answer10, answer11, answer12, answer13];

        const question3: Question = {
            id: uuidv4(),
            questionText: "Какое значение параметра Cipher Type в настройках точки доступа D-Link DWL-2100AP использовалось при выполнении лабораторной работы?",
            scoreWeight: 10,
            answers: array3
        }

        const answer14: Answer = {
            id: uuidv4(),
            answerText: "SSID",
            question: null,
            correct: true
        }

        const answer15: Answer = {
            id: uuidv4(),
            answerText: "WEP",
            question: null,
            correct: false
        }

        const answer16: Answer = {
            id: uuidv4(),
            answerText: "AES",
            question: null,
            correct: false
        }

        const answer17: Answer = {
            id: uuidv4(),
            answerText: "TKIP",
            question: null,
            correct: false
        }

        const answer18: Answer = {
            id: uuidv4(),
            answerText: "PSK",
            question: null,
            correct: false
        }

        var array4: Answer[] = [answer14, answer15, answer16, answer17, answer18];

        const question4: Question = {
            id: uuidv4(),
            questionText: "Точка доступа установлена на потолке Вашего офиса и подключена. Какой параметр должен быть (как минимум) настроен на точке доступа, чтобы обеспечить взаимодействие с ней беспроводных клиентов?",
            scoreWeight: 10,
            answers: array4
        }
        
        const answer19: Answer = {
            id: uuidv4(),
            answerText: "4",
            question: null,
            correct: true
        }

        const answer20: Answer = {
            id: uuidv4(),
            answerText: "1",
            question: null,
            correct: false
        }

        const answer21: Answer = {
            id: uuidv4(),
            answerText: "2",
            question: null,
            correct: false
        }

        const answer22: Answer = {
            id: uuidv4(),
            answerText: "3",
            question: null,
            correct: false
        }

        const answer23: Answer = {
            id: uuidv4(),
            answerText: "8",
            question: null,
            correct: false
        }

        var array5: Answer[] = [answer19, answer20, answer21, answer22, answer23];

        const question5: Question = {
            id: uuidv4(),
            questionText: "При выборе параметра (channel - канал) при настройке беспроводной точки доступа стандарта IEEE 802.11g следует задавать номер канала так, чтобы он отличался от номеров каналов соседних точек доступа с мощным сигнала не менее чем на:",
            scoreWeight: 10,
            answers: array5
        }

        return [question1, question2, question3, question4, question5];
    }

    getPreparedQuestions2(): Question[]{
        const answer1: Answer = {
            id: uuidv4(),
            answerText: "Помехи от беспроводных телефонов",
            question: null,
            correct: true
        }

        const answer2: Answer = {
            id: uuidv4(),
            answerText: "Мешают металлические шкафы",
            question: null,
            correct: true
        }

        const answer3: Answer = {
            id: uuidv4(),
            answerText: "Неподходящие антенны или неверное их направление/расположение",
            question: null,
            correct: true
        }

        const answer4: Answer = {
            id: uuidv4(),
            answerText: "Не настроен SSID",
            question: null,
            correct: false
        }

        const answer5: Answer = {
            id: uuidv4(),
            answerText: "Неверно настроен SSID",
            question: null,
            correct: false
        }

        var array1: Answer[] = [answer1, answer2, answer3, answer4, answer5];

        const question1: Question = {
            id: uuidv4(),
            questionText: "Одна точка доступа 802.11g была настроена и установлена в центре квадратного офиса. Некоторые пользователи испытывают замедление в связи и потерю пакетов, в то время, как большинство пользователей работают с сетью в полную силу. В чем может быть проблема?",
            scoreWeight: 10,
            answers: array1
        }


        const answer6: Answer = {
            id: uuidv4(),
            answerText: "Эти протоколы просты в вычислительном отношении",
            question: null,
            correct: true
        }

        const answer7: Answer = {
            id: uuidv4(),
            answerText: "Эти протоколы легко реализуются в крупных сетях",
            question: null,
            correct: false
        }

        const answer8: Answer = {
            id: uuidv4(),
            answerText: "Для них маловероятно бесконечное накапливание количества переходов(зацикливание в кольцевом маршруте)",
            question: null,
            correct: false
        }

        const answer9: Answer = {
            id: uuidv4(),
            answerText: "Хорошо работают в сетях любых размеров",
            question: null,
            correct: false
        }

        var array2: Answer[] = [answer6, answer7, answer8, answer9];

        const question2: Question = {
            id: uuidv4(),
            questionText: "В чем состоит преимущество дистанционно-векторных алгоритмов маршрутизации (DVA)?",
            scoreWeight: 15,
            answers: array2
        }
        
        const answer10: Answer = {
            id: uuidv4(),
            answerText: "Как можно ближе к отправителям трафика",
            question: null,
            correct: true
        }

        const answer11: Answer = {
            id: uuidv4(),
            answerText: "В сети Internet",
            question: null,
            correct: false
        }

        const answer12: Answer = {
            id: uuidv4(),
            answerText: "Ни в одном из перечисленных выше мест",
            question: null,
            correct: false
        }

        const answer13: Answer = {
            id: uuidv4(),
            answerText: "На магистральных каналах сети",
            question: null,
            correct: false
        }

        var array3: Answer[] = [answer10, answer11, answer12, answer13];

        const question3: Question = {
            id: uuidv4(),
            questionText: "Где предпочтительнее всего размещать расширенные списки управления доступом (ACL)?",
            scoreWeight: 10,
            answers: array3
        }

        const answer14: Answer = {
            id: uuidv4(),
            answerText: "Проверить, какой маршрут пройдет пакет по пути к точке назначения",
            question: null,
            correct: true
        }

        const answer15: Answer = {
            id: uuidv4(),
            answerText: "Создать карту устройств в сети",
            question: null,
            correct: false
        }

        const answer16: Answer = {
            id: uuidv4(),
            answerText: "Показать текущие настройки TCP/IP",
            question: null,
            correct: false
        }

        const answer17: Answer = {
            id: uuidv4(),
            answerText: "Проверить какой MAC-адрес устройства соответсвует его IP-адресу",
            question: null,
            correct: false
        }

        const answer18: Answer = {
            id: uuidv4(),
            answerText: "Показать значение MTU для каждого маршрутизатора в заданном маршруте до точки назначения",
            question: null,
            correct: false
        }

        var array4: Answer[] = [answer14, answer15, answer16, answer17, answer18];

        const question4: Question = {
            id: uuidv4(),
            questionText: "В чем цель использования команды traceroute?",
            scoreWeight: 10,
            answers: array4
        }
        
        const answer19: Answer = {
            id: uuidv4(),
            answerText: "Ни одного",
            question: null,
            correct: true
        }

        const answer20: Answer = {
            id: uuidv4(),
            answerText: "4",
            question: null,
            correct: false
        }

        const answer21: Answer = {
            id: uuidv4(),
            answerText: "2",
            question: null,
            correct: false
        }

        const answer22: Answer = {
            id: uuidv4(),
            answerText: "3",
            question: null,
            correct: false
        }

        const answer23: Answer = {
            id: uuidv4(),
            answerText: "1",
            question: null,
            correct: false
        }

        var array5: Answer[] = [answer19, answer20, answer21, answer22, answer23];

        const question5: Question = {
            id: uuidv4(),
            questionText: "Сколько неперекрывающихся каналов можно организовать в диапазоне 2,4 ГГц при использовании стандарта IEEE 802.11a?",
            scoreWeight: 10,
            answers: array5
        }

        return [question1, question2, question3, question4, question5];
    }
}