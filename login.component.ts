import { Component, OnInit } from '@angular/core';
import { User } from '../../Model/User.model';
import { CapstoreService } from '../../service/capstore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  custDetails: User[] = [];
  password;
  merchDetails: User[] = [];
  email;
  errorMessage;
  check;
  role;
  c = 0;
  confirmUser;
  constructor(
    private _capstoreService: CapstoreService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.check = this._capstoreService.getUserDetail();
    console.log(this._capstoreService.getCurrentMerchant());
    this.c = 0;
  }
  onSubmit(form) {
    // if (this.role == 'Customer') {
    //   for (let a of this.custDetails) {
    //     if (a.email == this.email) {
    //       this.confirmUser = a;
    //       this.c = this.c + 1;
    //     }
    //     if (a.password == this.password) this.c = this.c + 1;
    //   }
    //   if (this.c == 2) {
    //     this._capstoreService.setCurrentCustomer(this.confirmUser);
    //     //this._router.navigate(['/successpage']);
    //     this._capstoreService.setLoggedIn(true);
    //     console.log('Hello customer');
    //     this.errorMessage = '';
    //     this.c = 0;
    //   } else this.errorMessage = 'Invalid Credentials';
    // } else {
    //   console.log(this.password);
    //   console.log(form.value.selectedBy);

    //   for (let a of this.merchDetails) {
    //     if (a.email == this.email) {
    //       this.confirmUser = a;
    //       this.c = this.c + 1;
    //     }
    //     if (a.password == this.password) this.c = this.c + 1;
    //   }
    //   if (this.c == 2) {
    //     this._capstoreService.setCurrentMerchant(this.confirmUser);
    //     //this._router.navigate(['/successpage']);
    //     this._capstoreService.setLoggedIn(true);
    //     console.log('Hello merchant');
    //     this.errorMessage = '';
    //   } else this.errorMessage = 'Invalid Credentials';
    // }

    this._capstoreService
      .login(this.email, this.password, this.role)
      .subscribe((data) => this.storeData(data));
  }

  storeData(data) {
    if (this.role == 'Customer') {
      this.custDetails.push(data);
      this.router.navigate(['homepage']);
    } else {
      this.merchDetails.push(data);
      this.router.navigate(['home']);
    }
  }
}