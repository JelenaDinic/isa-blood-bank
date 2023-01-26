import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BloodSupplyService {

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getByCenterId(id: number): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + '/api/blood-supply/byCenter/' + id, {headers: this.headers});
  }

}
