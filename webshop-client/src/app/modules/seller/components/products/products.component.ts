import { EDIT_PRODUCT_PATH, SELLER_HOME_PATH } from './../../../../config/router-paths';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProductsService } from '../../../../services/products.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: [] = [];
  showDetails: boolean = false;
  showAddProductModal: boolean = false;
  productOnModal = {};

  constructor(private productsService: ProductsService,
              private router: Router,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getProducts();
  }

  private getProducts(): void {
    this.productsService.getProducts().subscribe(data => {
      this.products = data;
    }, error => {
      this.toastr.error('There was an error while getting your products');
    });
  }

  onClickShowDetails(product): void {
    this.showDetails = true;
    this.productOnModal = product;
  }

  onClickCloseModal(): void {
    this.showDetails = false;
  }

  onClickAdd(): void {
    this.showAddProductModal = true;
  }

  onClickCloseAddProductModal(): void {
    this.showAddProductModal = false;
    this.getProducts();
  }

  onClickDelete(id: number): void {
    this.productsService.delete(id).subscribe(data => {
      this.getProducts();
      this.toastr.success('Product has been deleted');
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

  onClickEdit(id: number): void {
    this.router.navigate([SELLER_HOME_PATH, EDIT_PRODUCT_PATH, id]);
  }
}
