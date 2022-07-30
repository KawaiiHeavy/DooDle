import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Group } from "../models/group.model";
import { Pageable } from "../models/pageable";

@Injectable({
    providedIn: "root",
})
export class GroupService { 

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    ngOnInit(): void {}

    public getAllGroups(): Observable<Group[]> {
        return this.http.get<Group[]>(`${this.apiServerUrl}/group/all`);
    }

    public addGroup(group: Group): Observable<Group> {
        return this.http.post<Group>(`${this.apiServerUrl}/group/add`, group);
    } 

    public updateGroup(group: Group): Observable<Group> {
        return this.http.put<Group>(`${this.apiServerUrl}/group/update`, group);
    }

    public deleteGroup(groupId: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/group/delete/${groupId}`);
    }

    public getGroupById(groupId: string): Observable<Group> {
        return this.http.get<Group>(`${this.apiServerUrl}/group/find/${groupId}`);
    }
    
    public getAllGroupPageable(page: number, size: number): Observable<Pageable<Group>> {
        return this.http.get<Pageable<Group>>(`${this.apiServerUrl}/group/allPageable?page=${page}&size=${size}`);
    }
}