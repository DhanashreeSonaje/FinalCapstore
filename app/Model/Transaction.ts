export class Transaction{
    addressId:number;
   // order_id:number;
    transactionDate:Date;
    transactionMoney:number;
    transactionMethod:String;//credit,debit,UPI,Wallet
    transactionStatus:String;//Success,Fail,Pending
}