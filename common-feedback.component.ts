import { Component, OnInit } from '@angular/core';
import { CommonFeedback} from 'src/app/model/CommonFeedBack';
import { Router } from '@angular/router';
import { CapstoreService } from 'src/app/service/capstore.service';


@Component({
  selector: 'app-cfd',
  templateUrl: './common-feedback.component.html'
})
export class CommonFeedbackComponent implements OnInit {

  cfdList: CommonFeedback[];
  searchTerm;


  constructor(private router:Router,
    private adminService: CapstoreService) { }

    approveRequest(feedbackId){
      this.adminService.forwardRequestToMerchant(feedbackId).subscribe();
  }

    approveResponse(feedbackId){
      console.log("approved")
      this.adminService.forwardResponseToCustomer(feedbackId).subscribe()
  }

  ngOnInit(){
    this.adminService.getAllCommonFeedback().subscribe(
        data=>{
            this.cfdList=data;
        },
        error=>{
            console.log(error);
        }
    )
  }


 back()
{
    this.router.navigate(['admin']);
}


}