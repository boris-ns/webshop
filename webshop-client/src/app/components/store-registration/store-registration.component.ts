import { StoreService } from './../../services/store.service';
import { StoreRegistrationDTO } from './../../models/store-registration.dto.model';
import { HOME_PATH } from './../../config/router-paths';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import UserRegistrationDTO from 'src/app/models/user-registration-dto.model';

@Component({
  selector: 'app-store-registration',
  templateUrl: './store-registration.component.html',
  styleUrls: ['./store-registration.component.css']
})
export class StoreRegistrationComponent implements OnInit {

  isUserInfoSent: boolean = false;
  registrationForm: FormGroup;

  constructor(private storeService: StoreService,
              private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private toastr: ToastrService) { 
}

  ngOnInit() {
    if (this.authService.isUserLoggedIn()) {
      this.toastr.warning('Please logout if you want to create a new account & store.', 'Warning');
      this.router.navigate([HOME_PATH]);
    }

    this.createForm();
  }

  private createForm(): void {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      repeatPassword: ['', Validators.required],
      storeName: ['', Validators.required],
      frequentBuyerDiscount: ['', Validators.required]
    });
  }

  onRegisterSubmit(): void {
    const password = this.registrationForm.controls.password.value;
    const repeatPassword = this.registrationForm.controls.repeatPassword.value;

    if (password !== repeatPassword) {
      this.toastr.warning('Passwords don\'t match', 'Warning');
      return;
    }

    const userInfo: UserRegistrationDTO = {
      username: this.registrationForm.controls.username.value,
      password: password,
      repeatPassword: repeatPassword,
      email: this.registrationForm.controls.email.value,
      name: this.registrationForm.controls.name.value,
    };

    const store: StoreRegistrationDTO = {
      user: userInfo,
      name: this.registrationForm.controls.storeName.value,
      frequentBuyerDiscount: this.registrationForm.controls.frequentBuyerDiscount.value
    };

    this.storeService.addStore(store).subscribe(data => {
      this.isUserInfoSent = true;
      this.toastr.success('You account has been created, go to your email to verify it.');
    }, error => {
      this.toastr.error('There was an error while adding your account. Try again later.');
    });
  }

}
