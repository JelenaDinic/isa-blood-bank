import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {
  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get<any>(this.apiHost + 'api/complaint', {headers: this.headers});
  }

  reply(replyText: string, complaintId: number): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/complaint/reply/' + replyText + '/' + complaintId, {headers: this.headers});

  }
}
