import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ADMIN_HOME_PATH, HOME_PATH, LOGIN_PATH, REGISTRATION_PATH } from './config/router-paths';
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
    path: ADMIN_HOME_PATH,
    loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
