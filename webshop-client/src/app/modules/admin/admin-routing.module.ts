import { HomeComponent } from './components/home/home.component';
import { PRODUCT_CATEGORIES_PATH } from './../../config/router-paths';
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
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule { }
  