package com.example.main.dao;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.main.exception.UserAlreadyExistsException;
import com.example.main.model.CommonFeedback;
import com.example.main.model.Coupon;
import com.example.main.model.CustomerDetails;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;
import com.example.main.model.User;

public interface AdminDao {
	
	//Merchant
	void removeMerchantById(int userId);
	void addMerchant(MerchantDetails merchant) throws MessagingException, UserAlreadyExistsException;
	MerchantDetails findMerchantById(int userId);
	MerchantDetails findMerchantByName(String name);
	MerchantDetails findMerchantByeMail(String geteMail);
	Iterable<MerchantDetails> findAllMerchants();
	boolean updateMerchant(MerchantDetails updatedMerchant);
	MerchantDetails userLogin(String token);
	boolean confirmAccount(String confirmationToken);
	MerchantDetails generateToken(String confirmationToken, String action) throws MessagingException;
	
	//product
	List<Product> getAllProducts();
	Product getProductByProductId(int productId);
	boolean updateCategoryByCategory(String productCategory, String updatedCategory);
	boolean update(Product product);
	boolean removeProduct(int productId);
	Product addProduct(Product product);
	
	
	//Customer
	void removeCustomerById(int userId);
	CustomerDetails findCustomerById(int userId);
	CustomerDetails findCustomerByName(String name);
	CustomerDetails findCustomerByEMailIgnoreCase(String eMail);
	boolean existingCustomerByEmail(String eMail);
	List<CustomerDetails> getAllCustomers();
	
	
	//Common feedbacks
	int forwardRequestToMerchant(int feedbackId);
	String forwardResponseToCustomer(int feedbackId);
	CommonFeedback getCommonFeedbackById(int feedbackId);
	List<CommonFeedback> getAllCommonFeedbackByProductId(int productId);
	List<CommonFeedback> getAllCommonFeedbackByUserId(int userId);
	List<CommonFeedback> getAll();
	
	//User userLogin(String[] userCredentials);
	
	//Coupon
	List<Coupon> getAllCoupons();
	Coupon getCouponById(int couponId) throws Exception;
	Coupon getCouponByCode(String couponCode);
	void addCoupon(@Valid Coupon coupon) throws MessagingException;
	Coupon generateCoupon(int couponId, int id) throws Exception;
	Coupon sendCoupon(int couponId) throws Exception;
	Boolean deleteCoupon(int couponId);
	
	
	
	
	
	
	
}
