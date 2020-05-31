import { BuyerRoutingModule } from './buyer-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdersComponent } from './components/orders/orders.component';
import { HomeComponent } from './components/home/home.component';



@NgModule({
  declarations: [OrdersComponent, HomeComponent],
  imports: [
    CommonModule,
    BuyerRoutingModule
  ]
})
export class BuyerModule { }
