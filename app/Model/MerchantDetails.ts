import { Product } from './Product';
import { UserAddress } from './UserAddress';
import { ProductFeedback } from './ProductFeedback';
import { Coupon } from './Coupon';
import { User } from './User';

export class MerchantDetails{
    userId:number;
    name:String;
    username:String;
    password:String;
    eMail:String;
    role:String;
    isActive:boolean;
    securityQuestion:String;
    securityAnswer:String;
	phoneNumber : String;
	alternatePhoneNumber: String;
    alternateEmail:String;
    products:Product;
    user_address:UserAddress;
    product_feedback:ProductFeedback;
    coupons:Coupon;
    isApproved:boolean;
    rating : number;
   // constructor(){}
    constructor(name,username,password,eMail,role,isActive,securityQuestion,securityAnswer,phoneNumber
        ,alternatePhoneNumber,alternateEmail,products,user_address,product_feedback,coupons,isApproved)
    {
        //this.userId=userId;
        this.username=username;
        this.password=password;
        this.eMail=eMail;
        this.role=role;
        this.isActive=isActive;
        this.securityQuestion=securityQuestion;
        this.securityAnswer=securityAnswer;
        this.phoneNumber=phoneNumber;
        this.alternatePhoneNumber=alternatePhoneNumber;
        this.alternateEmail=alternateEmail;
        this.products=products;
        this.user_address=user_address;
        this.product_feedback=product_feedback;
        this.coupons=coupons;
        this.isApproved=isApproved;
        


    }
    
}