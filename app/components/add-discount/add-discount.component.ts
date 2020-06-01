import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { CapstoreService } from 'src/app/service/capstore.service';
@Component({
  selector: 'app-add-discount',
  templateUrl: './add-discount.component.html',
  styleUrls: ['./add-discount.component.css']
})
export class AddDiscountComponent implements OnInit {


 
  productId:number;
  discount:number;
  check:any;
  message:any;
  submitted:boolean=false;
  discountForm:FormGroup;
  constructor(private router:Router,private adminService: CapstoreService,
    private route:ActivatedRoute,private formBuilder: FormBuilder) { }

  ngOnInit() {
    
    this.discountForm=this.formBuilder.group({
      productId:['',Validators.required],
      discount:['',Validators.required]
    });
   
  }
  back()
  {
      this.router.navigate(['admin']);
  }
  onSubmit()
  {
    
     this.submitted=true;
      this.productId=this.discountForm.controls.productId.value;
      this.discount=this.discountForm.controls.discount.value;
     // alert(this.productId);
      //alert(this.discount);
      if(this.discount>=0)
      {

      
    this.adminService.addDiscount(this.discount,this.productId).subscribe(
      data=>{
        console.log(data);
        this.check=data;
        if(this.check==true)
        {
          alert("Discount Added succesfully");
        }
        this.router.navigate(['showDiscount']);
      },
       error=>{
        console.log(error);
      }
    );
      }
      else{
        alert("Enter Valid Number");
      }
  }

  
}