import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Staff } from '../model/staff.model';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  create(staff: Staff): Observable<any> {
    return this.http.post<any>(this.apiServerUrl + '/api/staff', staff, {headers: this.headers}).pipe(catchError(this.handleValidationError));
  }
  getById(id: number): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + '/api/staff/' + id, {headers: this.headers});
  }
  update(staff: Staff): Observable<any> {
    return this.http.put<any>(this.apiServerUrl + '/api/staff/' + staff.id, staff, {headers: this.headers});
  }
  getStaffByCenterId(id: number): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + '/api/staff/all-bloodbank-staff/' + id, {headers: this.headers});
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
