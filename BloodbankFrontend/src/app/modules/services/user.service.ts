import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatLine } from '@angular/material/core';
import { Observable } from 'rxjs';
import {UserDisplayDTO} from '../dto/userDisplayDTO'
import { UserProfileDisplayDTO} from '../dto/userProfileDisplayDTO';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService{

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  public getAllUsers(page:number): Observable<UserDisplayDTO[]> {
    return this.http.get<UserDisplayDTO[]>(`${this.apiServerUrl}/api/registered-user/allUsers?page=`+ page +'&size=3')
  }
  public create(user: User): Observable<any> {
    return this.http.post<any>(this.apiServerUrl + '/api/registered-user/register', user, {headers: this.headers});
  }

  public findByEmail(email : string) : Observable<UserProfileDisplayDTO>{
    return this.http.get<UserProfileDisplayDTO>(`${this.apiServerUrl}/api/registered-user/byEmail/`+ email, {headers: this.headers})
  }

  public update(user: UserProfileDisplayDTO): Observable<UserProfileDisplayDTO>{
    return this.http.put<UserProfileDisplayDTO>(this.apiServerUrl + '/api/registered-user', user, {headers: this.headers})
  }

  public search(searchText : string = '') : Observable<UserDisplayDTO[]>{
    return this.http.get<UserDisplayDTO[]>(`${this.apiServerUrl}/api/registered-user/searchUsers?searchText=` + searchText, {headers: this.headers})
  }

  public getById(id: number): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + '/api/registered-user/' + id, {headers: this.headers});
  }
}
