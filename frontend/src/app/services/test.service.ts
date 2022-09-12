import { HttpClient } from "@angular/common/http";
import { Injectable, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Pageable } from "../models/pageable";
import { Test } from "../models/test.model";

@Injectable({
    providedIn: "root",
})
export class TestService implements OnInit {

    private apiServerUrl = environment.apiBaseUrl;
    private apiExtraUrl = environment.apiExtraUrl;

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

    public getAllTests(): Observable<Test[]> {
        return this.http.get<Test[]>(`${this.apiServerUrl}/test/all`);
    }

    public addTest(test: Test): Observable<Test> {
        console.log(test);
        return this.http.post<Test>(`${this.apiServerUrl}/test/add`, test);
    } 

    public updateTest(test: Test): Observable<Test> {
        return this.http.put<Test>(`${this.apiServerUrl}/test/update`, test);
    }

    public deleteTest(testId: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/test/delete/${testId}`);
    }

    public getTestById(testId: string): Observable<Test> {
        return this.http.get<Test>(`${this.apiServerUrl}/test/find/${testId}`);
    }
    
    public getAllTestPageable(page: number, size: number): Observable<Pageable<Test>> {
        return this.http.get<Pageable<Test>>(`${this.apiServerUrl}/test/allPageable?page=${page}&size=${size}`);
    }

    public checkTest(test: Test): Observable<Test> {
        return this.http.post<Test>(`${this.apiExtraUrl}/test/check`, test);
    }
}