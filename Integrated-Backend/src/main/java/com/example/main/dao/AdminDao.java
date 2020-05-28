package com.example.main.dao;

import java.util.List;
import java.util.Optional;

import com.example.main.model.CommonFeedback;
import com.example.main.model.Coupon;
import com.example.main.model.CustomerDetails;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;

public interface AdminDao {
	
	//Merchant
	void removeMerchantById(int userId);
	MerchantDetails addMerchant(MerchantDetails merchant);
	MerchantDetails findMerchantById(int userId);
	MerchantDetails findMerchantByName(String name);
	MerchantDetails findMerchantByeMail(String geteMail);
	Iterable<MerchantDetails> findAllMerchants();
	boolean updateMerchant(MerchantDetails updatedMerchant);
	
	
	//product
	List<Product> getAllProducts();
	Product getProductByProductId(int productId);
	List<Product> getFeaturedProducts();
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
	//int forwardRequestToMerchant(int feedbackId);
	String forwardResponseToCustomer(int feedbackId);
	CommonFeedback getCommonFeedbackById(int feedbackId);
	List<CommonFeedback> getAllCommonFeedbackByProductId(int productId);
	List<CommonFeedback> getAllCommonFeedbackByUserId(int userId);
	List<CommonFeedback> getAll();
	
	
	
	//coupon:
//	Coupon addCoupon(Coupon coupon);
//	Optional<Coupon> findById(int couponId);
//	Iterable<Coupon> findAllCoupons();
//	Coupon findByCouponCode(String code);
//	
	
	
	
	
	
	
}
