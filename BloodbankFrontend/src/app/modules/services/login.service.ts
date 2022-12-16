import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Staff } from '../model/staff.model';
import { LoginUser } from '../model/login-user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  login(loginUser: LoginUser): Observable<any> {
    console.log(loginUser);
    return this.http.post<any>(this.apiServerUrl + '/auth/login', loginUser, {headers: this.headers});
  }

}
