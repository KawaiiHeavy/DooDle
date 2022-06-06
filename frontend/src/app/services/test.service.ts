import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from "@angular/core";
import { QuestionBlank } from "../models/questionBlank.model";
import { Result } from "../models/result.model";
import { Test } from "../models/test.model";
import { TestBlank } from "../models/testBlank.model";

@Injectable({
    providedIn: "root",
})
export class TestService implements OnInit {

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

    getTests(){
        return this.http.get<Test[]>(`/api/tests/getTests`);
    }

    checkTest(testBlank: TestBlank){
        return this.http.post<Result>(`/api/tests/check`, testBlank);
    }

    saveTest(test: Test){
        return this.http.post<Test>(`/api/tests/saveTest`, test);
    }
}