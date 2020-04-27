import { SEASON_DISCOUNTS_URL, CATEGORY_DISCOUNTS_URL } from './../../../config/api-paths';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import AddSeasonDiscountDTO from 'src/app/models/add-season-discount-dto.model';
import AddCategoryDiscountDTO from 'src/app/models/add-category-discount-dto.model';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  constructor(private http: HttpClient) { 
  }

  getAllSeasonDiscounts(): Observable<any> {
    return this.http.get(SEASON_DISCOUNTS_URL);
  }

  addSeasonDiscount(discount: AddSeasonDiscountDTO): Observable<any> {
    return this.http.post(SEASON_DISCOUNTS_URL, discount);
  }

  deleteSeasonDiscount(id: number): Observable<any> {
    return this.http.delete(`${SEASON_DISCOUNTS_URL}/${id}`);
  }

  getAllCategoryDiscounts(): Observable<any> {
    return this.http.get(CATEGORY_DISCOUNTS_URL);
  }

  addCategoryDiscount(discount: AddCategoryDiscountDTO): Observable<any> {
    return this.http.post(CATEGORY_DISCOUNTS_URL, discount);
  }

  deleteCategoryDiscount(id: number): Observable<any> {
    return this.http.delete(`${CATEGORY_DISCOUNTS_URL}/${id}`);
  }
}
