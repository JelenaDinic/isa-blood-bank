import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FreeExamination } from '../model/free-examination.model';

@Injectable({
  providedIn: 'root'
})
export class FreeExaminationService {
  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  create(freeExamination: FreeExamination): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/free-examination', freeExamination, {headers: this.headers});

  }
}