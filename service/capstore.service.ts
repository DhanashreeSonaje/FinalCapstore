import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Product } from '../Model/Product.model';
import { Coupon } from '../Model/Coupon.model';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CapstoreService {
  private baseUrl = 'http://localhost:9096/capstore/admin';
  private loggedInStatus = JSON.parse(
    localStorage.getItem('loggedIn') || 'false'
  );
  constructor(private http: HttpClient) {}
  public registerCustomer(customer: Object): Observable<any> {
    return this.http.post(this.baseUrl + '/registerCustomer', customer);
  }
  public registerMerchant(merchant: Object): Observable<any> {
    return this.http.post(this.baseUrl + '/registerMerchant', merchant);
  }

  public login(email, password, role): Observable<any> {
    //this.setCurrentMerchant(email)
    return this.http.post(this.baseUrl + '/login', [email, password, role]);
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
      //  provide url for getting single product
      id
    );
  }

  get isLoggedIn() {
    return JSON.parse(
      localStorage.getItem('loggedIn') || this.loggedInStatus.toString()
    );
  }
  setLoggedIn(value: boolean) {
    this.loggedInStatus = value;
    localStorage.setItem('loggedIn', 'true');
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
  getUserDetail() {
    return 'ram@gmail.com';
  }
  getSomeData() {
    return 'bhaak';
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
    return this.http.get(`${this.baseUrl}`+"/customer/").pipe(catchError(this.handleError) );
  }
  deleteUser(Cust_ID:number)
  {
    return this.http.delete(`${this.baseUrl}/customer/${Cust_ID}`).pipe(catchError(this.handleError));
  }
 


  //------------------------------------------------Merchant----------------------------------------------------------------------------------------------
  getAllMerchant():Observable<any>
  {
    return this.http.get(`${this.baseUrl}`+"/getAllMerchants").pipe(catchError(this.handleError) );
  }
  getMerchant(id){
    return this.http.get(this.baseUrl+"/AllMerchants/"+id);
  }
  
  deleteMerchant(merchant_ID:number)
  {
    return this.http.delete(`${this.baseUrl}/merchant/${merchant_ID}`).pipe(catchError(this.handleError));
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
    return this.http.put<boolean>(`${this.baseUrl}/updateByCategory?productCategory=${productCategory}&updatedCategory=${updatedCategory}`,productCategory);
  }

  //------------------------------------------------Promocode----------------------------------------------------------------------------------------------
  createCoupon(coupons: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, coupons);
  }

  getCouponList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+`/coupons`);
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
 addDiscount(id,discount){
    return this.http.post(this.baseUrl+"/addDisount/"+id+"/",discount);
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
