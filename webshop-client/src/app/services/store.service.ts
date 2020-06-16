import { ADD_STORE_URL, STORES_URL } from './../config/api-paths';
import { Observable } from 'rxjs';
import { StoreRegistrationDTO } from './../models/store-registration.dto.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(private http: HttpClient) { 
  }

  getAll(): Observable<any> {
    return this.http.get(STORES_URL);
  }

  addStore(store: StoreRegistrationDTO): Observable<any> {
    return this.http.post(ADD_STORE_URL, store);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${STORES_URL}/${id}`);
  }
}
