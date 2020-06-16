import { ADD_STORE_URL } from './../config/api-paths';
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

  addStore(store: StoreRegistrationDTO): Observable<any> {
    return this.http.post(ADD_STORE_URL, store);
  }
}
