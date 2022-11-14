import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { BloodBankDisplayDTO } from "../dto/bloodBankDisplayDTO";

@Injectable({
    providedIn: 'root'
  })
  export class HomePageService {

    apiHost: string = 'http://localhost:8082/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(private http: HttpClient) { }

    getAll(): Observable<BloodBankDisplayDTO[]> {
      return this.http.get<BloodBankDisplayDTO[]>(this.apiHost + 'api/blood-bank-center/all-bloodbankDTOs', {headers: this.headers});
    }

  }