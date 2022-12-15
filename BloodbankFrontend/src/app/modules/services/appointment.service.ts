import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class AppointmentService {
  
    private apiServerUrl = 'http://localhost:8082';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(private http: HttpClient) { }

    findByUserId(id: number): Observable<any> {
      return this.http.get<any>(this.apiServerUrl + '/api/appointment/byUser/' + id, {headers: this.headers});
    }

    findByBloodBank(id: number) : Observable<any> {
      return this.http.get<any>(this.apiServerUrl + '/api/appointment/byBloodBank/' + id, {headers: this.headers});
    }
  
  }
  