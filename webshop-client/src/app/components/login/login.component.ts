import { ROLE_ADMIN, ROLE_BUYER, ROLE_SELLER } from './../../config/user-roles-keys';
import { USER_ID_KEY, USER_ROLE_KEY, USERNAME_KEY, USER_TOKEN_KEY } from './../../config/local-storage-keys';
import { AuthService } from './../../services/auth.service';
import { REGISTRATION_PATH, HOME_PATH, ADMIN_HOME_PATH, SELLER_HOME_PATH } from './../../config/router-paths';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import LoginDTO from 'src/app/models/login-dto.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup
  loginSuccess: boolean = false;
  loginError: boolean = false;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthService) { 
    this.createForm();
  }

  ngOnInit() {
    if (this.authService.isUserLoggedIn()) {
      this.router.navigate([HOME_PATH]);
    }
  }

  private createForm(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin(): void {
    const credentials: LoginDTO = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    };

    this.authService.login(credentials).subscribe(data => {
      localStorage.setItem(USER_ID_KEY, data.id);
      localStorage.setItem(USER_ROLE_KEY, data.authorities[0]);
      localStorage.setItem(USERNAME_KEY, data.username);
      localStorage.setItem(USER_TOKEN_KEY, data.token.accessToken);

      this.loginSuccess = true;
      this.loginError = false;

      // TODO: U zavisnosti od role prebaci korisnika na odredjeni homepage
      switch (data.authorities[0]) {
        case ROLE_ADMIN:  this.router.navigate([ADMIN_HOME_PATH]); break;
        case ROLE_SELLER: this.router.navigate([SELLER_HOME_PATH]); break;
        case ROLE_BUYER: break;
      }
    }, error => {
      this.loginSuccess = false;
      this.loginError = true;
    });
  }

  onClickRegister(): void {
    this.router.navigate([REGISTRATION_PATH]);
  }
}
