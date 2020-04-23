import { ProductsService } from './../../services/products.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: [] = [];
  showDetails: boolean = false;
  productOnModal = {};

  constructor(private productsService: ProductsService) { 
  }

  ngOnInit() {
    this.getProducts();
  }

  private getProducts(): void {
    this.productsService.getProducts().subscribe(data => {
      this.products = data;
      console.log(data);
    }, error => {
      // @TODO: dodati toster
      console.log(error);
    });
  }

  onClickShowDetails(product): void {
    console.log("KLIK")
    this.showDetails = true;
    this.productOnModal = product;
  }

  onClickCloseModal(): void {
    this.showDetails = false;
  }

}
