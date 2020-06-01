export class Coupon {
    couponId:number;
    couponCode:string;
    userId:number;
    couponStartDate:Date;
    couponEndDate:Date;
    couponAmount:number;
    couponMinOrderAmount:number;
    issuedBy:string;
    isApproved:boolean;
    // constructor(
    //   public couponEndDate: Date,
    //   public couponStartDate: Date,
    //   public couponAmount: number,
    //   public couponMinOrderAmount: number,
    //   public issusedBy: string
    // ) {}
  }
