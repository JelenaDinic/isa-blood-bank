import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Staff } from '../model/staff.model';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  create(staff: Staff): Observable<any> {
    return this.http.post<any>(this.apiServerUrl + '/api/staff', staff, {headers: this.headers});
  }
}
