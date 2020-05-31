package com.example.main.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.main.exception.UserAlreadyExistsException;
import com.example.main.model.CommonFeedback;
import com.example.main.model.Coupon;
import com.example.main.model.CustomerDetails;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;
import com.example.main.model.ProductFeedback;

public interface AdminService {

	//Merchant
	public void addMerchant(MerchantDetails m) throws MessagingException, UserAlreadyExistsException;
	public void removeMerchantById(int merchantId);
	public List<MerchantDetails> getAllMerchant();
	public boolean updateMerchant(MerchantDetails m);
	public MerchantDetails findMerchantById(int userId);
	public MerchantDetails verifyMerchantDetails(String email);
	public MerchantDetails userLogin(String confToken);
	public boolean confirmAccount(String confirmationToken);
	MerchantDetails generateToken(String confirmationToken, String action) throws MessagingException;
	
	//Products
	Product addProduct(Product product);
	boolean removeProduct(int productId);
	List<Product> getAllProducts();
	Product getProductByProductId(int productId);
	boolean update(Product product);
	boolean updateCategoryByCategory(String productCategory, String UpdatedCategory);
	
	
	//Customer
	void removeCustomerById(int userId);
	CustomerDetails findCustomerById(int userId);
	CustomerDetails findCustomerByName(String name);
	List<CustomerDetails> getAllCustomers();
	
	
	//Common feedbacks
	List<CommonFeedback> getAllCommonFeedbackByProductId(int productId);
	CommonFeedback getCommonFeedbackById(int feedbackId);
	List<CommonFeedback> getAllCommonFeedbackByUserId(int userId);
	int forwardRequestToMerchant(int feedbackId);
	String forwardResponseToCustomer(int feedbackId);
	List<CommonFeedback> getAll();
	
	
	//Product feedbacks
	ProductFeedback addCommonFeedback(ProductFeedback pfd);
	void removeProductFeedbackkById(int feedbackId);
	void removeProductFeedbackByUserId(int userId);
	List<ProductFeedback> getAllProductFeedbackByUserId(int userId);//LogicNotWritten
	ProductFeedback getProductFeedbackById(int feedbackId);
	List<ProductFeedback> getProductFeedbackByProductId(int productId);
	
	
	
	//Coupon:
	List<Coupon> getAllCoupons();
	Coupon getCouponById(int couponId) throws Exception;
	Coupon getCouponByCode(String couponCode);
	void addCoupon(@Valid Coupon coupon) throws MessagingException;
	Coupon generateCoupon(int couponId, int id) throws Exception;
	Coupon sendCoupon(int couponId) throws Exception;
	Boolean deleteCoupon(int couponId);
	
}
