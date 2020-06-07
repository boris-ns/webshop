import { ROLE_ADMIN, ROLE_BUYER, ROLE_SELLER } from './../../config/user-roles-keys';
import { AuthService } from './../../services/auth.service';
import { LOGIN_PATH, REGISTRATION_PATH, HOME_PATH, PRODUCT_CATEGORIES_PATH, ADMIN_HOME_PATH, STORES_PATH, SELLER_HOME_PATH, SELLER_MY_PRODUCTS_PATH, DISCOUNTS_PATH, BUYER_HOME_PATH, BUYER_MY_ORDERS_PATH, ADD_RULE_PATH } from './../../config/router-paths';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router,
              private authService: AuthService) {
  }

  ngOnInit() {
  }

  isUserLoggedIn(): boolean {
    return this.authService.isUserLoggedIn();
  }

  isAdminLoggedIn(): boolean {
    return this.authService.isUserRoleLoggedIn(ROLE_ADMIN);
  }

  isBuyerLoggedIn(): boolean {
    return this.authService.isUserRoleLoggedIn(ROLE_BUYER);
  }

  isSellerLoggedIn(): boolean {
    return this.authService.isUserRoleLoggedIn(ROLE_SELLER);
  }

  onClickLogin(): void {
    this.router.navigate([LOGIN_PATH]);
  }

  onClickRegister(): void {
    this.router.navigate([REGISTRATION_PATH]);
  }

  onClickLogout(): void {
    this.authService.logout();
    this.router.navigate([HOME_PATH]);
  }

  onClickCategories(): void {
    this.router.navigate([ADMIN_HOME_PATH, PRODUCT_CATEGORIES_PATH]);
  }

  onClickStores(): void {
    this.router.navigate([ADMIN_HOME_PATH, STORES_PATH]);
  }

  onClickProducts(): void {
    this.router.navigate([SELLER_HOME_PATH, SELLER_MY_PRODUCTS_PATH]);
  }

  onClickDiscounts(): void {
    this.router.navigate([ADMIN_HOME_PATH, DISCOUNTS_PATH]);
  }

  onClickMyOrders(): void {
    this.router.navigate([BUYER_HOME_PATH, BUYER_MY_ORDERS_PATH]);
  }

  onClickAddRule(): void {
    this.router.navigate([ADMIN_HOME_PATH, ADD_RULE_PATH]);
  }
}
