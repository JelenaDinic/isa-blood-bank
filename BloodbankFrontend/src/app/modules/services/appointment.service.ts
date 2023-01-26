import { Appointment } from './../model/appointment.model';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AppointmentDisplay } from '../model/appointment-display.model';
import { ScheduleAppointmentDTO } from '../dto/scheduleAppointmentDTO';
import { NewAppointmentDTO } from '../dto/newAppointmentDTO';

@Injectable({
    providedIn: 'root'
  })
  export class AppointmentService {
  
    private apiServerUrl = 'http://localhost:8082';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    private centerId!: number;
    private dateOfAppointment!: Date;
  
    constructor(private http: HttpClient) { }

    getCenterId(){return this.centerId;}

    setCenterId(id: number){this.centerId = id;}

    getDateOfAppointment(){return this.dateOfAppointment;}

    setDateOfAppointment(date: Date){this.dateOfAppointment = date;}

    findByUserId(id: number): Observable<any> {
      return this.http.get<any>(this.apiServerUrl + '/api/appointment/byUser/' + id, {headers: this.headers});
    }
    
    addPenalty(appointment: Appointment): Observable<any> {
      return this.http.post<any>(this.apiServerUrl + '/api/appointment/penalty' ,appointment, {headers: this.headers})
    }

    findByBloodBank(id: number) : Observable<any> {
      return this.http.get<any>(this.apiServerUrl + '/api/appointment/byBloodBank/' + id, {headers: this.headers});
    }

    getAllForScheduling(): Observable<AppointmentDisplay[]> {
      return this.http.get<AppointmentDisplay[]>(this.apiServerUrl + '/api/appointment/getAllForScheduling', {headers: this.headers});
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

    getAavailableNewAppointments(date: String): Observable<AppointmentDisplay[]> {
      return this.http.get<AppointmentDisplay[]>(this.apiServerUrl + '/api/appointment/availableNewAppointments/' + date, {headers: this.headers});
    }

    scheduleNewAppointment(appointment: NewAppointmentDTO): Observable<any> {
  
      return this.http.post<any>(this.apiServerUrl + '/api/appointment/scheduleNewAppointment/', appointment, {headers: this.headers})
    }
  }
  