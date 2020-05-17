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

  showBuy = false;

  coupon: string = '';
  quantity: number = 1;

  constructor(private toastr: ToastrService) { 
  }

  ngOnInit() {
    console.log(this.product);
  }

  onClickPlaceOrder() {
    console.log(this.product);
    console.log(this.coupon);
    this.showBuy = true;
  }

  onClickBuy() {
    // @TODO: implement this
    this.toastr.success('Product was successfully ordered');
  }
}
