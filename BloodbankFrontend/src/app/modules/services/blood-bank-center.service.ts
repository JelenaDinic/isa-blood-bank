import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { BloodBankCenter } from "../model/blood-bank-center.model";

@Injectable({
    providedIn: 'root'
  })
  export class BloodBankService {

    apiHost: string = 'http://localhost:8082/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(private http: HttpClient) { }

    getBloodBankCenter(id: number): Observable<any> {
        return this.http.get<any>(this.apiHost + 'api/blood-bank-center/' + id, {headers: this.headers});
      }
    
    update(bloodBankCenter: BloodBankCenter): Observable<any> {
        return this.http.put<BloodBankCenter>(this.apiHost + 'api/blood-bank-center/' + bloodBankCenter.id, bloodBankCenter, {headers: this.headers});
      }

    create(bloodBankCenter: BloodBankCenter): Observable<any> {
      return this.http.post<any>(this.apiHost + 'api/blood-bank-center', bloodBankCenter, {headers: this.headers});
    }
  }