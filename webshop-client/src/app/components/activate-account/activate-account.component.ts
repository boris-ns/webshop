import { LOGIN_PATH } from './../../config/router-paths';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit {

  private confirmationToken: string;

  constructor(private userService: UserService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { 
    this.activatedRoute.queryParams.subscribe(params => {
      this.confirmationToken = params['token'];
      this.activateAccount();
    });
  }

  ngOnInit() {
  }

  private activateAccount(): void {
    this.userService.verifyAccount(this.confirmationToken).subscribe(data => {
      this.router.navigate([LOGIN_PATH]);
      // @TODO: dodati toster
    }, error => {
      console.log(error);
      // @TODO: dodati toster
    });
  }
}
