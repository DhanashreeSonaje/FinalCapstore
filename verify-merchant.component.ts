import { Component, OnInit } from '@angular/core';
import { CapstoreService } from '../../service/capstore.service';
import { User } from '../../Model/User.model';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-verify-merchant',
  templateUrl: './verify-merchant.component.html',
  styleUrls: [],
})
export class VerifyMerchantComponent implements OnInit {
  merchant: Observable<User>;
  isApproved = false;
  email: string;
  token: string;
  constructor(
    private capstoreService: CapstoreService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.queryParams.forEach((params: Params) => {
      this.token = params['token'];
    });
    this.reloadData(this.token);
  }

  reloadData(token) {
    console.log('Hello');
    console.log(token);
    this.capstoreService.getMerchantForVerification(token).subscribe((data) => {
      this.merchant = data;
      console.log(data);
    });
  }

  acceptMerchant() {
    console.log('Accept');
    this.isApproved = true;
    this.capstoreService.getToken(this.token, 'Accept').subscribe((data) => {
      this.merchant = data;
      console.log(data);
    });
    //window.location.reload();
  }

  rejectMerchant() {
    console.log('Reject');
    this.isApproved = false;
    this.capstoreService.getToken(this.token, 'Reject').subscribe((data) => {
      this.merchant = data;
      console.log(data);
    });
    //window.location.reload();
  }

  closeTab() {
    window.close();
  }
}


