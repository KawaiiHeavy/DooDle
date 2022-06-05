import { Injectable, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../models/user.model";
import { Test } from "../models/test.model";


@Injectable({
    providedIn: "root",
})
export class UserService implements OnInit {

    private userUrl = 'http://localhost:9090/api/test/user';
    private trainerUrl = 'http://localhost:9090/api/test/trainer';
    private studentUrl = 'http://localhost:9090/api/test/student';
    private adminUrl = 'http://localhost:9090/api/test/admin';

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

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

    createUser(userNickname: string, userPassword: string, userEmail: string) {

        let user = new User(null, userNickname, userPassword, userEmail, null, null, null);

        console.log("It's working")
        return this.http.post<User>(`/api/users/save`, user);
    }

    getUsers(){
        console.log("It's working 2")
        return this.http.get<any[]>(`api/users/getAllUsers`);
    }

    getUserByNickname(nickname: string){
        return this.http.get<User>(`api/users/getUserByNickname/${nickname}`);
    }

    getUserOwnedTests(user: User){
        return this.http.get<Test[]>(`api/users/getTestsByUser/${user.id}`)
    }
}