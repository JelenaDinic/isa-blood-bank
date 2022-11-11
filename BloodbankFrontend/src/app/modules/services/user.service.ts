import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {UserDisplayDTO} from '../dto/userDisplayDTO'
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService{

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  public getAllUsers(): Observable<UserDisplayDTO[]> {
    return this.http.get<UserDisplayDTO[]>(`${this.apiServerUrl}/api/registered-user/allUsers`)
  }
  public create(user: User): Observable<any> {
    return this.http.post<any>(this.apiServerUrl + '/api/registered-user', user, {headers: this.headers});
  }
}
