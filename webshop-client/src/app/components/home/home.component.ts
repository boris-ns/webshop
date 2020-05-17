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

  products: [] = [];
  productToBuy: any = {};
  showBuyDialog = false;

  constructor(private productsService: ProductsService,
              private authService: AuthService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getProducts();
  }

  isUserLoggedIn(): boolean {
    return this.authService.isUserRoleLoggedIn(ROLE_BUYER);
  }

  getProducts(): void {
    this.productsService.getAll().subscribe(data => {
      this.products = data;
    }, error => {
      this.toastr.error('There was and error while getting the products.');
    });
  }

  onClickBuy(product) {
    this.productToBuy = product;
    this.showBuyDialog = true;
  }

  onClickModalClose() {
    this.showBuyDialog = false;
  }
}
