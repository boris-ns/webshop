import { PRODUCT_ALL_CATEGORIES_URL } from './../config/api-paths';
import { AddProductCategoryDTO } from '../models/add-product-category-dto.model';
import { PRODUCT_CATEGORIES_URL } from '../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductCategoriesService {

  constructor(private http: HttpClient) { 
  }

  getAll(): Observable<any> {
    return this.http.get(PRODUCT_ALL_CATEGORIES_URL);
  }

  add(category: AddProductCategoryDTO): Observable<any> {
    return this.http.post(PRODUCT_CATEGORIES_URL, category);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${PRODUCT_CATEGORIES_URL}/${id}`);
  }
}
