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

    constructor(private http: HttpClient){}

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