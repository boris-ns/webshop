import { PlaceOrderDTO } from './../../models/place-order-dto.model';
import { ProductsService } from './../../services/products.service';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent implements OnInit {

  @Input()
  product: any = {};
  
  showOrderInfo = false;
  order = {};

  coupon: string = '';
  quantity: number = 1;

  constructor(private toastr: ToastrService,
              private productService: ProductsService) { 
  }

  ngOnInit() {
  }

  onClickPlaceOrder() {
    console.log(this.product);
    console.log(this.coupon);
  
    const order: PlaceOrderDTO = {
      productId: this.product.id,
      quantity: this.quantity,
      coupon: (this.coupon) ? this.coupon : null
    };

    this.productService.buy(order).subscribe(data => {
      this.order = data;
      this.toastr.success('Product was successfully ordered');
      this.showOrderInfo = true;
    }, error => {
      this.toastr.error(error.error.message);
    });
  }

}
