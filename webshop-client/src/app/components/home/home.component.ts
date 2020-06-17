import { ProductsFilterDTO } from './../../models/products-filter.dto.model';
import { ProductCategoriesService } from './../../services/product-categories.service';
import { ToastrService } from 'ngx-toastr';
import { ROLE_BUYER } from './../../config/user-roles-keys';
import { AuthService } from './../../services/auth.service';
import { ProductsService } from './../../services/products.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  recommendedProducts: [] = [];
  products: [] = [];
  productToBuy: any = {};
  showBuyDialog = false;
  categories: [] = [];

  filterName: string;
  filterCategory: string;
  filterDownPrice: number;
  filterTopPrice: number;
  filterFreeShipping: boolean;

  constructor(private productsService: ProductsService,
              private authService: AuthService,
              private categoriesService: ProductCategoriesService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getProducts();
    this.getCategories();

    if (this.isUserLoggedIn()) {
      this.getRecommendedProducts();
    }
  }

  isUserLoggedIn(): boolean {
    return this.authService.isUserRoleLoggedIn(ROLE_BUYER);
  }

  private getProducts(): void {
    this.productsService.getAll().subscribe(data => {
      this.products = data;
    }, error => {
      this.toastr.error('There was an error while getting the products.');
    });
  }

  private getCategories(): void {
    this.categoriesService.getAll().subscribe(data => {
      this.categories = data;
    }, error => {
      this.toastr.error('There was an error while getting the product categories');
    });
  }

  private getRecommendedProducts(): void {
    this.productsService.getRecommendedProducts().subscribe(data => {
      this.recommendedProducts = data;
    }, error => {
      this.toastr.error('There was an error while getting recommended products.');
    });
  }

  onClickBuy(product) {
    this.productToBuy = product;
    this.showBuyDialog = true;
  }

  onClickModalClose() {
    this.showBuyDialog = false;
  }

  onClickSearch(): void {
    const filter: ProductsFilterDTO = {
      name: this.filterName,
      categoryName: (this.filterCategory === 'None') ? null : this.filterCategory,
      downPrice: this.filterDownPrice,
      topPrice: this.filterTopPrice,
      isFreeShipping: this.filterFreeShipping
    }

    console.log(filter);

    this.productsService.filter(filter).subscribe(data => {
      this.products = data;
    }, error => {
      this.toastr.error('There was an error while filtering products');
    });
  }

  onClickClearSearch(): void {
    this.filterName = null;
    this.filterCategory = null;
    this.filterDownPrice = null;
    this.filterTopPrice = null;
    this.filterFreeShipping = null;
  
    this.getProducts();
  }
}
