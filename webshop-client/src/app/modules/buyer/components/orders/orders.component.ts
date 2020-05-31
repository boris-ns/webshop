import { ToastrService } from 'ngx-toastr';
import { OrdersService } from './../../../../services/orders.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: [] = [];

  constructor(private ordersService: OrdersService,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getOrders();
  }

  private getOrders(): void {
    this.ordersService.getMyOrders().subscribe(data => {
      this.orders = data;
    }, error => {
      this.toastr.error('There was an error while getting the orders.');
    });
  }

}
