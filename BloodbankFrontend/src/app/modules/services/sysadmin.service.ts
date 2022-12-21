import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { PasswordChangeDTO } from '../dto/passwordChangeDTO';
import { Sysadmin } from '../model/sysadmin.model';

@Injectable({
  providedIn: 'root'
})
export class SysadminService {
  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  create(sysadmin: Sysadmin): Observable<any> {
    return this.http.post<any>(this.apiServerUrl + '/api/system-admin', sysadmin, {headers: this.headers});
  }

  changePassword(passwordChangeDTO: PasswordChangeDTO): Observable<any> {
    return this.http.post<any>(this.apiServerUrl + '/api/system-admin/changePassword', passwordChangeDTO, {headers: this.headers});
  }

  private handleValidationError(error: HttpErrorResponse) {
    var map = new Map<string, string>();
    Object.keys(error.error).forEach(key => {  
      map.set(key, error.error[key] )
    });
    localStorage.setItem('errormap', JSON.stringify(Array.from(map.entries())));
    
    return throwError(() => new Error(error.status +'\n'+ error.error))
  }
}
