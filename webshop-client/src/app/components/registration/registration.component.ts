import { ToastrService } from 'ngx-toastr';
import { HOME_PATH, LOGIN_PATH } from './../../config/router-paths';
import { AuthService } from './../../services/auth.service';
import { Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import UserRegistrationDTO from 'src/app/models/user-registration-dto.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  isUserInfoSent: boolean = false;
  registrationForm: FormGroup;

  constructor(private userService: UserService,
              private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private toastr: ToastrService) { 
  }

  ngOnInit() {
    if (this.authService.isUserLoggedIn()) {
      this.toastr.warning('Please logout if you want to create a new account.', 'Warning');
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
      repeatPassword: ['', Validators.required]
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

    this.userService.register(userInfo).subscribe(data => {
      this.isUserInfoSent = true;
      this.toastr.success('You account has been created, go to your email to verify it.');
    }, error => {
      this.toastr.error('There was an error while adding your account. Try again later.');
    });
  }
}
