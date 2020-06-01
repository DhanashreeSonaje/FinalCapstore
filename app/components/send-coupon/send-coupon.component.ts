import { Component, OnInit } from '@angular/core';
import { Coupon } from '../../Model/Coupon.model';
import { CapstoreService } from '../../service/capstore.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-send-coupon',
  templateUrl: './send-coupon.component.html',
  styleUrls: ['./send-coupon.component.css']
})
export class SendCouponComponent implements OnInit {

  coupon: Observable<Coupon[]>;
  couponId:number;
  couponCode:string;
  submitted=false;
  error=null;
  subId=true;
  subCode=true;
  no:number;
  coupon1: Coupon = new Coupon();
  isApproved= false;


  constructor(private capstoreService: CapstoreService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
      this.route.params.subscribe(params => {
      console.log(params);
      this.couponId=params['couponId'];
      console.log(this.couponId);
      console.log(this.isApproved);
   }); 
    this.capstoreService.getCouponById(this.couponId, this.coupon)
    .subscribe((data) => {
      console.log(data)
      this.coupon1=data;
    })
  }

  acceptCoupon(couponId) {
    console.log("Accept");
    console.log(this.couponId);
    this.isApproved=true;
    this.capstoreService.getCurrentMerchant();
    this.capstoreService.sendCoupon(couponId,this.coupon1)
    .subscribe(data =>{ this.coupon1=data
      console.log(data)});
  }

  
    back() {
      this.submitted=false;
      this.subId=true;
      this.subCode=true;
    }

}
