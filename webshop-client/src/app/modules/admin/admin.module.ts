import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductCategoriesComponent } from './components/product-categories/product-categories.component';
import { HomeComponent } from './components/home/home.component';
import { StoresComponent } from './components/stores/stores.component';
import { DiscountComponent } from './components/discount/discount.component';
import { AddSeasonDiscountComponent } from './components/add-season-discount/add-season-discount.component';
import { AddCategoryDiscountComponent } from './components/add-category-discount/add-category-discount.component';
import { AddRuleComponent } from './components/add-rule/add-rule.component';


@NgModule({
  declarations: [ProductCategoriesComponent, HomeComponent, StoresComponent, DiscountComponent, AddSeasonDiscountComponent, AddCategoryDiscountComponent, AddRuleComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
