import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/model/Coupon';
import { Observable } from 'rxjs';
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


  constructor(private couponService: CapstoreService, private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
     this.couponService.getCouponList()
     .subscribe(data=>{
        this.coupon=data;
      },
      error=>{
        console.log(error);
      }
    );
  }

  searchById(){
    this.subId=false;
    this.subCode=true;
  }

  searchByCode(){
    this.subId=true;
    this.subCode=false;
  }

  deletePromocode(couponId)
  {
    this.couponId=couponId.value;
    this.couponService.deleteCoupon(couponId).subscribe(data=>{console.log(data)},error=>{console.log(error)});
    window.location.reload();
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
