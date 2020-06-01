import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/model/Coupon';
import { CapstoreService } from 'src/app/service/capstore.service';

@Component({
  selector: 'app-show-promocode',
  templateUrl: './show-promocode.component.html',
  styleUrls: ['./show-promocode.component.css']
})
export class ShowPromocodeComponent implements OnInit {

  searchTerm ;
  coupon: Coupon[];
  couponId:number;
  couponCode:string;
  submitted=false;
  error=null;
  subId=true;
  subCode=true;
  
  coupon1: Coupon = new Coupon();
  errorMessage: string;


  constructor(private couponService: CapstoreService, private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    console.log(this.couponId)
     this.couponService.getCouponList()
     .subscribe(data=>{
        this.coupon=data;
        this.couponId=data.couponId;
      },
      error=>{
        this.error=error.error.message;
        console.log(error.error.message);
      }
    );
  }

  deleteCoupon(couponId:number)
  {
    console.log(typeof(couponId))
    this.couponService.deleteCoupon(couponId)
    .subscribe(data=>{console.log(data)
      if(data===true){
        window.location.reload();
      }
    },
    error=>{console.log(error)});
  }

  backToCoupons() {
    this.submitted=false;
    this.subId=true;
    this.subCode=true;
  }

  back()
  {
    this.router.navigate(['admin']);
  }

}
