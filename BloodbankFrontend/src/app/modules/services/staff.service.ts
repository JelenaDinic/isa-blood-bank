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
  getById(id: number): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + '/api/staff/' + id, {headers: this.headers});
  }
  update(staff: Staff): Observable<any> {
    return this.http.put<any>(this.apiServerUrl + '/api/staff/' + staff.id, staff, {headers: this.headers});
  }
  getStaffByCenterId(id: number): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + '/api/staff/all-bloodbank-staff/' + id, {headers: this.headers});
  }
}
