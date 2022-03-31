import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../models/user.model";

const httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json",
    "Authorization": `Bearer ${localStorage.getItem("jwt")}`,}),
  };

@Injectable({
    providedIn: "root",
})
export class UserService {

    private userUrl = 'http://localhost:9090/api/test/user';
    private trainerUrl = 'http://localhost:9090/api/test/trainer';
    private studentUrl = 'http://localhost:9090/api/test/student';
    private adminUrl = 'http://localhost:9090/api/test/admin';

    constructor(private http: HttpClient){}

    getUserBoard(): Observable<string> {
        return this.http.get(this.userUrl, { responseType: 'text' });
    }
    
    getAdminBoard(): Observable<string> {
        return this.http.get(this.adminUrl, { responseType: 'text' });
    }
    
    getTrainerBoard(): Observable<string> {
        return this.http.get(this.trainerUrl, { responseType: 'text' });
    }

    getStudentBoard(): Observable<string> {
        return this.http.get(this.studentUrl, { responseType: 'text' });
    }

    public createUser(userNickname: string, userPassword: string, userEmail: string) {

        let user = new User(null, userNickname, userPassword, userEmail, null, null, null);

        console.log("It's working")

        return this.http.post<User>(`/api/users/save`, user);
    }

    public getUsers(): Observable<any>{

        console.log("It's working 2")

        return this.http.get(`api/users/get`);
    }
}