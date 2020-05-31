import { MY_ORDERS_URL } from './../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) {
  }

  getMyOrders(): Observable<any> {
    return this.http.get(MY_ORDERS_URL);
  }
}
