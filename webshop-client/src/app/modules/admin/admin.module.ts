import { AdminRoutingModule } from './admin-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCategoriesComponent } from './components/product-categories/product-categories.component';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [ProductCategoriesComponent, HomeComponent],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
