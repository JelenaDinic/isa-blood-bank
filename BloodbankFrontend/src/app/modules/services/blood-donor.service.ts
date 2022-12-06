import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodDonor } from '../model/blood-donor-form.model';

@Injectable({
  providedIn: 'root'
})
export class BloodDonorService{

  private apiServerUrl = 'http://localhost:8082';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  public create(bloodDonor: BloodDonor): Observable<any> {
    return this.http.post<any>(this.apiServerUrl + '/api/blood-donor', bloodDonor, {headers: this.headers});
  }
  public checkIfAllowed(id: number): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + "/api/blood-donor/check/" + id, {headers: this.headers} )
  }
  
}
  

