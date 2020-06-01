
import { Transaction } from './Transaction';

export class Order{
    orderId:number;
    orderAmount:number;
    orderStatus:String;
    products:[{
    userId:number,
    productId:number
    }];
    
    addressId:number;
    transaction:Transaction;
}