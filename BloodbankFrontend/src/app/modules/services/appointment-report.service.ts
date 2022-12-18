import { AppointmentReport } from './../model/appointment-report.model';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class AppointmentReportService {
  
    apiHost: string =  'http://localhost:8082';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(private http: HttpClient) { }

    create(appointmentReport: AppointmentReport): Observable<any> {
        return this.http.post<any>(this.apiHost + '/api/appointment-report', appointmentReport, {headers: this.headers});
      }

  }