import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCategoriesComponent } from './components/product-categories/product-categories.component';
import { HomeComponent } from './components/home/home.component';
import { StoresComponent } from './components/stores/stores.component';


@NgModule({
  declarations: [ProductCategoriesComponent, HomeComponent, StoresComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
  ]
})
export class AdminModule { }
