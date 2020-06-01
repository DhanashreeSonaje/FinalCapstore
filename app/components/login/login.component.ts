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
  
  user=new User();
  password;
  email;
  errorMessage='';
  error=null;
  
  confirmUser;
  constructor(
    private _capstoreService: CapstoreService,
    private router: Router
  ) {}

  ngOnInit()  {

  }
  
  onSubmit(form) {
    console.log("hello")
    if(this.email==="dsonaje6@gmail.com" && this.password==="dhanu@123"){
      sessionStorage.setItem('email',this.user.email);
       this._capstoreService.isUserLoggedIn()
       console.log("Login Successful");
       alert("Login Successful")
      this.router.navigate(['admin'])
    }
    else{
        console.log("error")
         this.errorMessage = "Sorry, Invalid credentials!!!" ;
    }
  }
}
