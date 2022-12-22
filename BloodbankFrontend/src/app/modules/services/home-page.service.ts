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



    public getAll(page:number): Observable<BloodBankDisplayDTO[]> {
      return this.http.get<BloodBankDisplayDTO[]>(`${this.apiHost}api/blood-bank-center/all-bloodBankDTOs?page=`+ page +'&size=3')
    }

    searchBloodBanks(searchName : string = '', searchCity : string = '', filterByRating : number = 0, sortByParam: string = '', sortDirection: string = '') : Observable<BloodBankDisplayDTO[]>{
      return this.http.get<BloodBankDisplayDTO[]>(`${this.apiHost}api/blood-bank-center/searchBanks?searchName=` + searchName + "&searchCity=" + searchCity + "&filterByRating=" + filterByRating + "&sortByParam=" + sortByParam + "&sortDirection=" + sortDirection, {headers: this.headers})
    }

  }