import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from "@angular/core";
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
}