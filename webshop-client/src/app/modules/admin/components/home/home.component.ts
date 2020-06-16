import { PRODUCT_CATEGORIES_PATH, STORES_PATH, DISCOUNTS_PATH, ADD_RULE_PATH } from './../../../../config/router-paths';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ADMIN_HOME_PATH } from 'src/app/config/router-paths';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { 
  }

  ngOnInit() {
  }

  onClickCategories() {
    this.router.navigate([ADMIN_HOME_PATH, PRODUCT_CATEGORIES_PATH]);
  }
  
  onClickStores() {
    this.router.navigate([ADMIN_HOME_PATH, STORES_PATH]);
  }
  
  onClickDiscounts() {
    this.router.navigate([ADMIN_HOME_PATH, DISCOUNTS_PATH]);
  }
  
  onClickAddRules() {
    this.router.navigate([ADMIN_HOME_PATH, ADD_RULE_PATH]);
  }
}
