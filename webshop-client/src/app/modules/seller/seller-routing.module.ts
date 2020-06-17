import { EditStoreComponent } from './components/edit-store/edit-store.component';
import { ProductsComponent } from './components/products/products.component';
import { SELLER_MY_PRODUCTS_PATH, EDIT_STORE_PATH } from './../../config/router-paths';
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
    },
    {
        path: EDIT_STORE_PATH,
        component: EditStoreComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SellerRoutingModule { }
  