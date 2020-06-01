import { CommonFeedback } from './CommonFeedback';
import { Cart } from './Cart';
import { ProductFeedback } from './ProductFeedback';
import { UserAddress } from './UserAddress';
import { Order } from './Order';
import { User } from './User';

export class CustomerDetails  extends User{
    userId:number;
	phoneNumber : String;
	alternatePhoneNumber: String;
    alternateEmail:String;
	address:String;
	common_feedback:CommonFeedback;
	product_feedback:ProductFeedback;
	orders:Order;
	cart:Cart;
	addresses:UserAddress;

	constructor(name: any, username: any, password: any, eMail: any, role: any, isActive: any, securityQuestion: any, securityAnswer: any,phone_number: any,alternate_phone_number: any,alternate_email: any,
		address: any,common_feedback: any,product_feedback: any,orders: any,cart: any,addresses: any)
	{
	
		super(name,username,password,eMail,role,isActive,securityQuestion,securityAnswer);
		this.phoneNumber=phone_number;
		this.alternatePhoneNumber=alternate_phone_number;
		this.alternateEmail=alternate_email;
		this.address=address;
		this.common_feedback=common_feedback;
		this.product_feedback=product_feedback;
		this.orders=orders;
		this.cart=cart;
		this.addresses=addresses;

	}
	
   
}