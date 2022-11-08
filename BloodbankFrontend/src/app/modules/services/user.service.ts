import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {UserDisplayDTO} from '../dto/userDisplayDTO'

@Injectable({
  providedIn: 'root'
})
export class UserService{

  private apiServerUrl = 'http://localhost:8082';

  constructor(private http: HttpClient) { }

  public getAllUsers(): Observable<UserDisplayDTO[]> {
    return this.http.get<UserDisplayDTO[]>(`${this.apiServerUrl}/api/registered-user/allUsers`)
  }
}
