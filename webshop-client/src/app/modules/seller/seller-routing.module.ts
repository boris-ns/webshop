import { ProductsComponent } from './components/products/products.component';
import { SELLER_MY_PRODUCTS_PATH } from './../../config/router-paths';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: SELLER_MY_PRODUCTS_PATH,
        component: ProductsComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SellerRoutingModule { }
  