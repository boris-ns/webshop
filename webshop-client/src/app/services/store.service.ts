import { EditStoreDTO } from './../models/edit-store.dto.model';
import { ADD_STORE_URL, STORES_URL, MY_STORE_URL } from './../config/api-paths';
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

  getMyStore(): Observable<any> {
    return this.http.get(MY_STORE_URL);
  }

  addStore(store: StoreRegistrationDTO): Observable<any> {
    return this.http.post(ADD_STORE_URL, store);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${STORES_URL}/${id}`);
  }

  edit(store: EditStoreDTO): Observable<any> {
    return this.http.put(STORES_URL, store);
  }
}
