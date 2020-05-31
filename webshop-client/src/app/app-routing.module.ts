import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ADMIN_HOME_PATH, HOME_PATH, LOGIN_PATH, REGISTRATION_PATH, SELLER_HOME_PATH, VERIFY_ACCOUNT, BUYER_HOME_PATH } from './config/router-paths';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: HOME_PATH,
    component: HomeComponent
  },
  {
    path: LOGIN_PATH,
    component: LoginComponent
  },
  {
    path: REGISTRATION_PATH,
    component: RegistrationComponent
  },
  {
    path: VERIFY_ACCOUNT,
    component: ActivateAccountComponent
  },
  {
    path: BUYER_HOME_PATH,
    loadChildren: () => import('./modules/buyer/buyer.module').then(m => m.BuyerModule)
  },
  {
    path: ADMIN_HOME_PATH,
    loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: SELLER_HOME_PATH,
    loadChildren: () => import('./modules/seller/seller.module').then(m => m.SellerModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
