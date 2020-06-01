import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Product } from '../Model/Product.model';
import { Coupon } from '../Model/Coupon.model';
import { catchError } from 'rxjs/operators';
import { CommonFeedback } from '../Model/CommonFeedback';
import { User } from '../Model/User.model';

@Injectable({
  providedIn: 'root',
})
export class CapstoreService {
  
  private baseUrl = 'http://localhost:9096/capstore/admin';

  public loginUserFromRemote(user : User): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}` + `/login`, user)
    .pipe(catchError(this.handleError));
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('email')
    console.log(!(user === null))
    return !(user === null)
  }


  constructor(private http: HttpClient) {}
  public registerCustomer(customer: Object): Observable<any> {
    return this.http.post(this.baseUrl + '/registerCustomer', customer);
  }
  public registerMerchant(merchant: Object): Observable<any> {
    return this.http.post(this.baseUrl + '/registerMerchant', merchant);
  }

  public logOut() {
    sessionStorage.removeItem('email')
  }

  public getUser(tk): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/confirm-account?token=' + tk);
  }

  getCategory(category) {
    return this.http.get<Product[]>(
      this.baseUrl + '/productCategory/' + category
    );
  }

  getDiscount(category, discountPercent): Observable<any> {
    return this.http.get<Product[]>(
      this.baseUrl + '/discountCategory/' + category + '/' + discountPercent
    );
  }
  getSearchProducts(category) {
    return this.http.get<Product[]>(
      this.baseUrl + '/searchProducts/' + category
    );
  }
  getProduct(id) {
    return this.http.get<Product>(
      id
    );
  }

  setCurrentCustomer(user) {
    localStorage.setItem('customer', JSON.stringify(user));
  }
  getCurrentCustomer() {
    return localStorage.getItem('customer');
  }
  setCurrentMerchant(user) {
    localStorage.setItem('merchant', JSON.stringify(user));
  }
  getCurrentMerchant() {
    return localStorage.getItem('merchant');
  }
  
  getMerchantForVerification(token: String): Observable<any> {
    return this.http.get(this.baseUrl + '/getMerchant?token=' + token);
  }

  getToken(token: string, action: string): Observable<any> {
    return this.http.get(
      this.baseUrl + '/generateToken?token=' + token + '&action=' + action
    );
  }

  

  //------------------------------------------------user-----------------------------------------------------------------------------------------------------------------

  getAllUser():Observable<any>
  {
    return this.http.get(`${this.baseUrl}`+"/getAllCustomers/").pipe(catchError(this.handleError) );
  }

  deleteUser(Cust_ID:number)
  {
    return this.http.delete(`${this.baseUrl}/deleteCustomer/${Cust_ID}`).pipe(catchError(this.handleError));
  }
 


  //------------------------------------------------Merchant----------------------------------------------------------------------------------------------
  getAllMerchant():Observable<any>
  {
    return this.http.get(`${this.baseUrl}`+"/getAllMerchants").pipe(catchError(this.handleError) );
  }
  getMerchant(id){
    return this.http.get(this.baseUrl+"/AllMerchants/"+id);
  }
  
  deleteMerchant(merchant_ID)
  {
    return this.http.delete(`${this.baseUrl}/deleteMerchant/${merchant_ID}`).pipe(catchError(this.handleError));
  }
  
  updateMerchant(merchant){
    let options = {
      method: "POST",
      body: JSON.stringify(merchant),
      headers: new Headers({ "Content-Type": "application/json" }),
    };
    return fetch(this.baseUrl + "/updateMerchant", options);
  //return this.http.post(this.baseUrl+"/update",merchant);
  }
   inviteservice(email){
    return this.http.get(this.baseUrl+"/invite/"+email);
  }

  //------------------------------------------------Product------------------------------------------------------------------------------------------------
  
  getAllProducts():Observable<any>
  {
    return this.http.get(`${this.baseUrl}`+"/getAllProducts").pipe(catchError(this.handleError));
  }
 

  addProduct(product: Object): Observable<Object>{
    return this.http.post<number>(`${this.baseUrl}/addProduct`, product);
  }

  removeProduct(productId: number): Observable<Object>{
    return this.http.delete<boolean>(`${this.baseUrl}/deleteProduct/${productId}`);
  }

  update(product: Object): Observable<Object>{
    return this.http.put<boolean>(`${this.baseUrl}/updateProduct`,product);
  }

  getProductById(productId: number): Observable<Object>{
    return this.http.get<Product>(`${this.baseUrl}/getProductById/${productId}`);
  }

  updateCategoryByCategory(productCategory: String, updatedCategory: String):Observable<Object>{
    return this.http.put<boolean>(`${this.baseUrl}/updateCategoryByCategory?productCategory=${productCategory}&updatedCategory=${updatedCategory}`,productCategory);
  }

  //------------------------------------------------Promocode----------------------------------------------------------------------------------------------
  createCoupon(coupons: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, coupons);
  }

  getCouponList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+`/coupons`).pipe(catchError(this.handleError));
  }

  getCouponById(couponId: number, value: any): Observable<Coupon> {
    return this.http.put<Coupon>(`${this.baseUrl}/Id/${couponId}`, value);
  }

  getCouponByCode(couponCode: string, value: any): Observable<Coupon> {
    return this.http.put<Coupon>(`${this.baseUrl}/Code/${couponCode}`, value);
  }

  getCoupon(couponId: number,userId: number, value:any):Observable<any> {
    return this.http.put(`${this.baseUrl}`+`/generateCoupon/`+couponId+`/`+userId, value);
  }

  sendCoupon(couponId: number,value:any):Observable<any> {
    return this.http.put(`${this.baseUrl}`+`/sendCoupon/`+couponId, value);
  }

  deleteCoupon(couponId:number)
  {
    console.log(couponId)
    return this.http.delete(`${this.baseUrl}/coupon/${couponId}`).pipe(catchError(this.handleError));
  }
  
  
  //------------------------------------------------Discount----------------------------------------------------------------------------------------------
  addDiscount( discount:number,productID:number): Observable<any> {
    
    return this.http.put(`${this.baseUrl}/addDiscount/${discount}/${productID}`,discount);
  }
   
  //------------------------------------------------CommonFeedback-----------------------------------------------------------------------
  forwardRequestToMerchant(feedbackId: number):Observable<number>{
    return this.http.get<number>(`${this.baseUrl}/forwardRequestToMerchant/${feedbackId}`);
  }


  forwardResponseToCustomer(feedbackId:number):Observable<string>{
    return this.http.get<string>(`${this.baseUrl}/forwardResponseToCustomer/${feedbackId}`);

  }

  getAllCommonFeedback(){
    return this.http.get<CommonFeedback[]>(`${this.baseUrl}/getAllCommonFeedback`);
  }
  
  //------------------------------------------------Error Handling--------------------------------------------------------
  handleError(error) {
    let errorMessage='';
    let msg='';
    if(error.error instanceof ErrorEvent)
    {
       
        errorMessage=`${error.errorMessage}`;
        console.log("Client Side");
    }
    else{

 
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      console.log("Server SIde");

    }
    console.log(errorMessage);
    return throwError(error);
  }

}
