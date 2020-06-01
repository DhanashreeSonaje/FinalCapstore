import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { VerficationComponent } from './components/signup/verification.component';
import { ProductPageComponent } from './components/product-page/product-page.component';
import { VerifyMerchantComponent } from './components/verify-merchant/verify-merchant.component';
import { ShowCouponComponent } from './components/show-coupon/show-coupon.component';
import { SendCouponComponent } from './components/send-coupon/send-coupon.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { AdminComponent } from './components/admin/admin.component';
import { AddDiscountComponent } from './components/add-discount/add-discount.component';
import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { MerchantListComponent } from './components/merchant-list/merchant-list.component';
import { ShowPromocodeComponent } from './components/show-promocode/show-promocode.component';
import { ShowDiscountComponent } from './components/show-discount/show-discount.component';
import { ThirdPartyMerchantComponent } from './components/third-party-merchant/third-party-merchant.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { UpdateCategoryComponent } from './components/update-category/update-category.component';
import { CommonFeedbackComponent } from './components/common-feedback/common-feedback.component';
import { AddPromocodeComponent } from './components/add-promocode/add-promocode.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path:'admin',component:AdminComponent},
  { path: 'signup', component: SignupComponent },
  { path: 'verify', component: VerficationComponent },
  { path: 'verifyMerchant', component: VerifyMerchantComponent },
  { path:'showMerchant',component:MerchantListComponent},
  { path:'thirdparty',component:ThirdPartyMerchantComponent},
  { path:'login', component:LoginComponent },
  
  { path:'addPromocode',component:AddPromocodeComponent},
  { path:'showPromocode',component:ShowPromocodeComponent},
  { path: 'applyCoupon/:couponId/:userId', component:ShowCouponComponent},
  { path: 'sendCoupon/:couponId', component:SendCouponComponent},
  
  { path: 'addProducts',component:AddProductComponent},
  { path:'productList', component:ProductListComponent},
  { path: 'productpage', component: ProductPageComponent },
  { path:'showProduct',component:ProductListComponent},
  { path:'updateProduct',component:UpdateProductComponent},
  { path:'updateCategory',component:UpdateCategoryComponent},
  
  { path:'addDiscount',component:AddDiscountComponent},
  { path:'showDiscount',component:ShowDiscountComponent},

  
  { path:'showCustomer',component:CustomerListComponent},
  
  { path:'PendingFeedbacks',component:CommonFeedbackComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
export const RoutingComponents = [
  SignupComponent,
  VerficationComponent,
];

