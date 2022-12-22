import { Observable } from 'rxjs';
import { Equipment } from './../model/equipment.model';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class EquipmentService {

    apiHost: string = 'http://localhost:8082';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient) { }

    updateEquipment(equipmentArr: Equipment[]): Observable<any> {
        return this.http.put<any>(this.apiHost + '/api/equipment/updateQuantity', equipmentArr, { headers: this.headers });
    }

}