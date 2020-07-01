import { EditProductDTO } from './../../../../models/edit-product-dto.model';
import { ProductsService } from './../../../../services/products.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  private productId: number;

  editProduct: EditProductDTO = {};

  constructor(private route: ActivatedRoute,
              private toastr: ToastrService,
              private productService: ProductsService) { 
  }

  ngOnInit() {
    this.productId = +this.route.snapshot.paramMap.get('id');
    this.getProduct();
  }

  private getProduct() {
    this.productService.getProduct(this.productId).subscribe(data => {
      this.editProduct = data;
      console.log(this.editProduct);
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

  onClickEdit() {
    this.productService.edit(this.editProduct).subscribe(data => {
      this.editProduct = data;
      console.log(this.editProduct);
    }, error => {
      this.toastr.error(error.error.message);
    });
  }
}
