import { Appointment } from './../model/appointment.model';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AppointmentDisplay } from '../model/appointment-display.model';
import { ScheduleAppointmentDTO } from '../dto/scheduleAppointmentDTO';

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
    
    addPenalty(appointment: Appointment): Observable<any> {
      return this.http.post<any>(this.apiServerUrl + '/api/appointment/penalty' ,appointment, {headers: this.headers})
    }

    findByBloodBank(id: number) : Observable<any> {
      return this.http.get<any>(this.apiServerUrl + '/api/appointment/byBloodBank/' + id, {headers: this.headers});
    }

    getAll(): Observable<AppointmentDisplay[]> {
      return this.http.get<AppointmentDisplay[]>(this.apiServerUrl + '/api/appointment', {headers: this.headers});
    }

    getAllScheduled(id: number): Observable<AppointmentDisplay[]> {
      return this.http.get<AppointmentDisplay[]>(this.apiServerUrl + '/api/appointment/scheduledAppointmentsForUser/' + id, {headers: this.headers});
    }

    scheduleAppointment(appointment: ScheduleAppointmentDTO): Observable<any> {
      console.log(appointment)
      return this.http.post<any>(this.apiServerUrl + '/api/appointment/scheduleAppointment/', appointment, {headers: this.headers})
    }

    cancelAppointment(appointment: ScheduleAppointmentDTO): Observable<any> {
      console.log(appointment)
      return this.http.post<any>(this.apiServerUrl + '/api/appointment/cancel/', appointment, {headers: this.headers})
    }

    verifyScheduling(activationCode: String): Observable<any> {
      return this.http.post<any>(this.apiServerUrl + '/api/appointment/QRcodeVerification/' ,activationCode, {headers: this.headers})
    }
  }
  