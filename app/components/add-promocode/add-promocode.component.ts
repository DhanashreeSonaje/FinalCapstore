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
  error;
  constructor(private adminService:CapstoreService, private router: Router, private fb:FormBuilder) { }

  ngOnInit() {
  }

  save() {
    this.adminService.createCoupon(this.coupons)
    .subscribe(data =>{ 
      this.submitted = true;
      console.log(data)
    }, error =>{ 
       this.error=error.error.message;
       console.log(error.error.message);
      }); 
  }

  onSubmit(){
    this.save();
  }
  
  back()
  {
    this.router.navigate(['admin']);
  }
}
