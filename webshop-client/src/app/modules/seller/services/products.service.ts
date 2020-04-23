import { MY_PRODUCTS_URL } from './../../../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { 
  }

  getProducts(): Observable<any> {
    return this.http.get(MY_PRODUCTS_URL);
  }
}
