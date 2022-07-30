import { Injectable, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../models/user.model";
import { environment } from "src/environments/environment";
import { Pageable } from "../models/pageable";


@Injectable({
    providedIn: "root",
})
export class UserService implements OnInit {

    private apiServerUrl = environment.apiBaseUrl;

    private userUrl = environment.apiBaseUrl + '/user/userPage';
    private trainerUrl = environment.apiBaseUrl + '/user/trainerPage';
    private studentUrl = environment.apiBaseUrl + '/user/studentPage';
    private adminUrl = environment.apiBaseUrl + '/api/user/adminPage';

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

    public getUserBoard(): Observable<string> {
        return this.http.get(this.userUrl, { responseType: 'text' });
    }
    
    public getAdminBoard(): Observable<string> {
        return this.http.get(this.adminUrl, { responseType: 'text' });
    }
    
    public getTrainerBoard(): Observable<string> {
        return this.http.get(this.trainerUrl, { responseType: 'text' });
    }

    public getStudentBoard(): Observable<string> {
        return this.http.get(this.studentUrl, { responseType: 'text' });
    }

    public getAllUsers(): Observable<User[]> {
        return this.http.get<User[]>(`${this.apiServerUrl}/user/all`);
    }

    public addUser(user: User): Observable<User> {
        return this.http.post<User>(`${this.apiServerUrl}/user/add`, user);
    } 

    public updateUser(user: User): Observable<User> {
        return this.http.put<User>(`${this.apiServerUrl}/user/update`, user);
    }

    public deleteUser(userId: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/user/delete/${userId}`);
    }

    public getUserById(userId: string): Observable<User> {
        return this.http.get<User>(`${this.apiServerUrl}/user/find/${userId}`);
    }
    
    public getAllUserPageable(page: number, size: number): Observable<Pageable<User>> {
        return this.http.get<Pageable<User>>(`${this.apiServerUrl}/user/allPageable?page=${page}&size=${size}`);
    }

}