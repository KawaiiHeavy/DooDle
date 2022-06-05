import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from "@angular/core";
import { QuestionBlank } from "../models/questionBlank.model";
import { Result } from "../models/result.model";
import { Test } from "../models/test.model";

@Injectable({
    providedIn: "root",
})
export class TestService implements OnInit {

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

    getTests(){
        return this.http.get<Test[]>(`/api/tests/getTests`);
    }

    checkTest(test: QuestionBlank[]){
        return this.http.post<Result>(`/api/tests/check`, test)
    }
}