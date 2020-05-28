export class Product {
    constructor(
      public productId: number,
      public productName: string,
      public productImage: string,
      public productPrice: number,
      public productRating: number,
      public noOfProductViewed: number,
      public productBrand: string,
      public noOfProducts: number,
      public productInfo: string,
      public discount: number,
      public productCategory: string,
      public productActivated: boolean,
      public status: boolean,
      public featured: boolean,
      public userId: number
    ) {}
  }