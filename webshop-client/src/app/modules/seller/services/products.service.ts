import { Observable } from 'rxjs';
import { MY_PRODUCTS_URL, PRODUCTS_URL } from '../../../config/api-paths';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import AddProductDTO from 'src/app/models/add-product-dto.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { 
  }

  getProducts(): Observable<any> {
    return this.http.get(MY_PRODUCTS_URL);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${PRODUCTS_URL}/${id}`);
  }

  add(product: AddProductDTO): Observable<any> {
    return this.http.post(PRODUCTS_URL, product);
  }
}
