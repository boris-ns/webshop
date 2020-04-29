import { ToastrService } from 'ngx-toastr';
import { ProductCategoriesService } from './../../../../services/product-categories.service';
import { ProductsService } from '../../../../services/products.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import AddProductDTO from 'src/app/models/add-product-dto.model';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  addForm: FormGroup;
  categories: [] = [];

  constructor(private fb: FormBuilder,
              private productsService: ProductsService,
              private categoriesService: ProductCategoriesService,
              private toastr: ToastrService) { 
    this.createForm();
  }

  ngOnInit() {
    this.getCategories();
  }

  private getCategories(): void {
    this.categoriesService.getAll().subscribe(data => {
      this.categories = data;
    }, error => {
      this.toastr.error('There was an error while getting the product categories');
    });
  }

  private createForm(): void {
    this.addForm = this.fb.group({
      name: ['', Validators.required],
      categoryId: ['', Validators.required],
      price: ['', Validators.required],
      shipping: ['', Validators.required],
      quantity: ['', Validators.required],
      maxQuantity: ['', Validators.required],
      maxOrderQuantity: ['', Validators.required],
      quantityDiscount: ['', Validators.required],
      orderQuantityDiscount: ['', Validators.required],
      discount: ['', Validators.required],
      maxDiscount: ['', Validators.required],
      coupon: ['', Validators.required],
      couponDiscount: ['', Validators.required],
    });
  }

  onAdd(): void {
    const product: AddProductDTO = {
      name: this.addForm.value.name,
      categoryId: this.addForm.value.categoryId,
      price: this.addForm.value.price,
      shippingPrice: this.addForm.value.shipping,
      quantity: this.addForm.value.quantity,
      maxQuantity: this.addForm.value.maxQuantity,
      maxOrderQuantity: this.addForm.value.maxOrderQuantity,
      quantityDiscount: this.addForm.value.quantityDiscount / 100,
      orderQuantityDiscount: this.addForm.value.orderQuantityDiscount / 100,
      discount: this.addForm.value.discount / 100,
      maxDiscount: this.addForm.value.maxDiscount / 100,
      coupon: this.addForm.value.coupon,
      couponDiscount: this.addForm.value.couponDiscount / 100
    };

    this.productsService.add(product).subscribe(data => {
      this.toastr.success('Product has been successfully added');
      // @TODO: poslati dodati element nazad na prvu komponentu nekako
      // @TODO: da li cemo zatvarati formu ili je ostaviti otvorenu ?
      // @TODO: treba uraditi clear forme
    }, error => {
      // @TODO: dodati toster
      console.log(error);
    });

  }
}
