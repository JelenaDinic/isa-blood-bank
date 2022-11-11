import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { BloodBankCenter } from "../model/blood-bank-center.model";

@Injectable({
    providedIn: 'root'
  })
  export class HomePageService {

    apiHost: string = 'http://localhost:8082/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(private http: HttpClient) { }

    getAll(): Observable<BloodBankCenter[]> {
      return this.http.get<BloodBankCenter[]>(this.apiHost + 'api/blood-bank-center', {headers: this.headers});
    }

  }