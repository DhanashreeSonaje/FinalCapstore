import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/model/Coupon';
import { CapstoreService } from 'src/app/service/capstore.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-add-promocode',
  templateUrl: './add-promocode.component.html',
  styleUrls: ['./add-promocode.component.css']
})
export class AddPromocodeComponent implements OnInit {

  coupons: Coupon=new Coupon();
  submitted = false;
  
  constructor(private adminService:CapstoreService, private router: Router, private fb:FormBuilder) { }

  ngOnInit() {
  }

  save() {
    this.adminService.createCoupon(this.coupons)
    .subscribe(data =>{ 
      console.log(data)
    }, error =>{ 
       console.log(error.error.message);
      }); 
  }

  onSubmit(){
    this.submitted = true;
    this.save();
  }
  back()
  {
    this.router.navigate(['admin']);
  }
}
