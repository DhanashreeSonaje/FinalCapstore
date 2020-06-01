import { Component, OnInit } from '@angular/core';
import { User } from '../../Model/User.model';
import { ConfirmEqualValidatorDirective } from '../../Shared/confirm-equal-validator.directive';
import { CapstoreService } from '../../service/capstore.service';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-verify',
  templateUrl: './verification.component.html',
  styleUrls: [],
})
export class VerficationComponent implements OnInit {
  token;
  flag = false;
  constructor(
    private _capstoreService: CapstoreService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.forEach((params: Params) => {
      this.token = params['token'];
    });
    console.log(this.token);

    this._capstoreService
      .getUser(this.token)
      .subscribe((error) => console.log(error));
  }

  goToLogin() {
    this.router.navigate(['']);
  }
}