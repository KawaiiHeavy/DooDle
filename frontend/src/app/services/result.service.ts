import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Group } from "../models/group.model";
import { Pageable } from "../models/pageable";
import { Result } from "../models/result.model";

@Injectable({
    providedIn: "root",
})
export class ResultService { 

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

    public getAllResults(): Observable<Result[]> {
        return this.http.get<Result[]>(`${this.apiServerUrl}/result/all`);
    }

    public addResult(result: Result): Observable<Result> {
        return this.http.post<Result>(`${this.apiServerUrl}/result/add`, result);
    } 

    public updateResult(result: Result): Observable<Result> {
        return this.http.put<Result>(`${this.apiServerUrl}/result/update`, result);
    }

    public deleteResult(resultId: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/result/delete/${resultId}`);
    }

    public getResultById(resultId: string): Observable<Result> {
        return this.http.get<Result>(`${this.apiServerUrl}/result/find/${resultId}`);
    }
    
    public getAllResultPageable(page: number, size: number): Observable<Pageable<Result>> {
        return this.http.get<Pageable<Result>>(`${this.apiServerUrl}/result/allPageable?page=${page}&size=${size}`);
    }

    public findResultsByTest(testId: string): Observable<Result[]> {
        return this.http.get<Result[]>(`${this.apiServerUrl}/result/findByTest/${testId}`);
    }
}