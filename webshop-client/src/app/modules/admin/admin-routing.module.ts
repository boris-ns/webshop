import { AddRuleComponent } from './components/add-rule/add-rule.component';
import { DiscountComponent } from './components/discount/discount.component';
import { StoresComponent } from './components/stores/stores.component';
import { HomeComponent } from './components/home/home.component';
import { PRODUCT_CATEGORIES_PATH, STORES_PATH, DISCOUNTS_PATH, ADD_RULE_PATH } from './../../config/router-paths';
import { ProductCategoriesComponent } from './components/product-categories/product-categories.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: PRODUCT_CATEGORIES_PATH,
        component: ProductCategoriesComponent
    },
    {
        path: STORES_PATH,
        component: StoresComponent
    },
    {
        path: DISCOUNTS_PATH,
        component: DiscountComponent
    },
    {
        path: ADD_RULE_PATH,
        component: AddRuleComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule { }
  