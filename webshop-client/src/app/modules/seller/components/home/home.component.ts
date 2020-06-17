import { SELLER_HOME_PATH, SELLER_MY_PRODUCTS_PATH, EDIT_STORE_PATH } from './../../../../config/router-paths';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

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

  onClickProducts() {
    this.router.navigate([SELLER_HOME_PATH, SELLER_MY_PRODUCTS_PATH]);
  }

  onClickEditStore() {
    this.router.navigate([SELLER_HOME_PATH, EDIT_STORE_PATH]);
  }
}
