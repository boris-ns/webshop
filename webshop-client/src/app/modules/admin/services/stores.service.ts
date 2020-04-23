import { STORES_URL } from './../../../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StoresService {

  constructor(private http: HttpClient) { 
  }

  getAll(): Observable<any> {
    return this.http.get(STORES_URL);
  }
}
