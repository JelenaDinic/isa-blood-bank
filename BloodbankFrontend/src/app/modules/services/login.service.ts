import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { LoginUser } from '../model/login-user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json'});


  constructor(private http: HttpClient) { }

  login(loginUser: LoginUser): Observable<any> {
    console.log(loginUser);
    return this.http.post(this.apiServerUrl + '/authenticate', loginUser, {headers: this.headers, responseType: 'text'});
  }


  roleMatch(allowedRoles: string[]): boolean {
    var isMatch = false;
    var token = localStorage.getItem('token');
    if(token == null)
      token = "";
    var payLoad = JSON.parse(window.atob(token.split('.')[1]));
    var userRole = payLoad.role;
    allowedRoles.forEach(element => {
      if (userRole == element) {
        isMatch = true;
      }
    });
    return isMatch;
  }
}