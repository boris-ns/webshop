import { EditProductDTO } from './../models/edit-product-dto.model';
import { ProductsFilterDTO } from './../models/products-filter.dto.model';
import { PLACE_ORDER_URL, RECOMMENDED_PRODUCTS_URL, FILTER_PRODUCTS_URL, GET_PRODUCT_URL, EDIT_PRODUCT_URL } from './../config/api-paths';
import { PlaceOrderDTO } from './../models/place-order-dto.model';
import { ALL_PRODUCTS_URL } from '../config/api-paths';
import { Observable } from 'rxjs';
import { MY_PRODUCTS_URL, PRODUCTS_URL } from '../config/api-paths';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import AddProductDTO from 'src/app/models/add-product-dto.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { 
  }

  getProduct(id: number): Observable<any> {
    return this.http.get(`${GET_PRODUCT_URL}/${id}`);
  }

  getAll(): Observable<any> {
    return this.http.get(ALL_PRODUCTS_URL);
  }

  getProducts(): Observable<any> {
    return this.http.get(MY_PRODUCTS_URL);
  }

  getRecommendedProducts(): Observable<any> {
    return this.http.get(RECOMMENDED_PRODUCTS_URL);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${PRODUCTS_URL}/${id}`);
  }

  add(product: AddProductDTO): Observable<any> {
    return this.http.post(PRODUCTS_URL, product);
  }

  buy(order: PlaceOrderDTO): Observable<any> {
    return this.http.post(PLACE_ORDER_URL, order);
  }

  filter(filter: ProductsFilterDTO): Observable<any> {
    return this.http.post(FILTER_PRODUCTS_URL, filter);
  }

  edit(product: EditProductDTO): Observable<any> {
    return this.http.put(EDIT_PRODUCT_URL, product);
  }
}
