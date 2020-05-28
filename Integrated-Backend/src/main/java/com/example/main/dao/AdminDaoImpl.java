package com.example.main.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.main.model.CommonFeedback;
import com.example.main.model.CustomerDetails;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;
import com.example.main.repository.CommonFeedbackRepository;
import com.example.main.repository.CouponRepository;
import com.example.main.repository.CustomerRepository;
import com.example.main.repository.MerchantRepository;
import com.example.main.repository.ProductFeedbackRepository;
import com.example.main.repository.ProductRepository;



@Component("AdminComponent")
public class AdminDaoImpl implements AdminDao{

	
	@Autowired
	@Qualifier(value="MerchantRepository")
	private MerchantRepository merchantRepository;
	
	@Autowired
	@Qualifier(value="CustomerRepository")
	private CustomerRepository customerRepository;
	
	@Autowired
	@Qualifier(value="ProductRepository")
	private ProductRepository productRepository;
	
	@Autowired
	@Qualifier(value="ProductFeedbackRepository")
	private ProductFeedbackRepository productFeedbackRepository;
	
	@Autowired
	@Qualifier(value="CommonFeedbackRepository")
	private CommonFeedbackRepository commonFeedbackRepository;
	
	@Autowired
	@Qualifier(value="CouponRepository")
	private CouponRepository couponRepository;
	
	
	
	
	
	
	
	//Merchant:
	
	@Override
	public void removeMerchantById(int userId) {
		merchantRepository.deleteById(userId);
		
	}

	@Override
	public MerchantDetails addMerchant(MerchantDetails merchant) {
		merchantRepository.save(merchant);
		return merchant;
	}

	@Override
	public MerchantDetails findMerchantById(int userId) {
		return merchantRepository.findById(userId).get();
	}

	@Override
	public MerchantDetails findMerchantByName(String name) {
		return merchantRepository.findByName(name);
	}
	
	@Override
	public MerchantDetails findMerchantByeMail(String eMail) {
		return merchantRepository.findByEmail(eMail);
	}

	
	@Override
	public Iterable<MerchantDetails> findAllMerchants() {
		return  merchantRepository.findAll();
	}
	
	@Override
	public boolean updateMerchant(MerchantDetails updatedMerchant) {
		boolean exists = merchantRepository.existsById(updatedMerchant.getUserId());
		if(exists==true) {
		MerchantDetails merchant = findMerchantById(updatedMerchant.getUserId());
		if(updatedMerchant.getPhoneNumber()!=null)
			merchant.setPhoneNumber(updatedMerchant.getPhoneNumber());
		if(updatedMerchant.getAlternatePhoneNumber()!=null)
			merchant.setAlternatePhoneNumber(updatedMerchant.getAlternatePhoneNumber());
		if(updatedMerchant.getAddresses()!=null)
			merchant.setAddresses(updatedMerchant.getAddresses());
		if(updatedMerchant.getAlternateEmail()!=null)
			merchant.setAlternateEmail(updatedMerchant.getAlternateEmail());
		if(updatedMerchant.getName()!=null)
			merchant.setName(updatedMerchant.getName());
		merchantRepository.save(merchant);
		return true;
		}
		return false;
	}
	
	//Product
	
	@Override
	public Product addProduct(Product product) {
		productRepository.save(product);
		return product;
	}
	
	@Override
	public boolean removeProduct(int productId) {
		boolean exists = productRepository.existsById(productId);
		if(exists==true) {
		productRepository.deleteById(productId);
		return true;
		}
		else
			return false;
	}
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductByProductId(int productId) {
		return productRepository.findById(productId).get();
	}
	
	@Override
	public boolean update(Product product) {
		boolean exists = productRepository.existsById(product.getProductId());
		if(exists==true) {
		Product p = productRepository.findById(product.getProductId()).get();
		p.setFeatured(product.isFeatured());
		if(product.getNoOfProducts()!=0)
		p.setNoOfProducts(product.getNoOfProducts());
		if(product.getNoOfProductViewed()!=0)
		p.setNoOfProductViewed(product.getNoOfProductViewed());
		p.setProductActivated(product.isProductActivated());
		if(product.getProductBrand()!=null)
		p.setProductBrand(product.getProductBrand());
		if(product.getProductCategory()!=null)
		p.setProductCategory(product.getProductCategory());
		if(product.getProductImage()!=null)
		p.setProductImage(product.getProductImage());
		if(product.getProductInfo()!=null)
		p.setProductInfo(product.getProductInfo());
		if(product.getProductName()!=null)
		p.setProductName(product.getProductName());
		if(product.getProductPrice()!=0)
		p.setProductPrice(product.getProductPrice());
		if(product.getProductRating()!=0)
		p.setProductRating(product.getProductRating());
		p.setStatus(product.isStatus());
		productRepository.save(p);
		return true;
		}
		return false;
	}

	/**
	 * Changes the name of a category. All the products having that category gets changed to another category
	*/
	@Override
	public boolean updateCategoryByCategory(String productCategory, String updatedCategory) {
		List<Product> products = productRepository.findAllByProductCategory(productCategory);
		Iterator<Product> i = products.iterator();
		while(i.hasNext()) {
			Product pr= (Product)i.next();
			pr.setProductCategory(updatedCategory);
		}
		productRepository.saveAll(products);
		return true;
	}

// 	@Override
// 	public boolean updateCategoryById(int productId, String updatedCategory) {
// 		Product p = productRepository.findById(productId).get();
// 		p.setProductCategory(updatedCategory);
// 		productRepository.save(p);
// 		return true;
// 	}

	@Override
	public List<Product> getFeaturedProducts() {
		List<Product> products= getAllProducts();
		Iterator<Product> i = products.iterator();
		while(i.hasNext()) {
			Product pr= (Product)i.next();
			if(pr.isFeatured()==false) {
				i.remove();
			}
		}
		return products;
	}

		
	//Customer
	
	@Override
	public void removeCustomerById(int userId) {
		customerRepository.deleteById(userId);
	}


	@Override
	public CustomerDetails findCustomerById(int userId) {
		return customerRepository.findById(userId).get();
	}

	@Override
	public CustomerDetails findCustomerByName(String name) {
		return customerRepository.findByName(name);
	}
	
	@Override
	public CustomerDetails findCustomerByEMailIgnoreCase(String eMail) {
		return customerRepository.findByEmail(eMail);
	}

	
	@Override
	public List<CustomerDetails> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@Override
	public boolean existingCustomerByEmail(String eMail) {
		boolean result = false;
		if (customerRepository.existsByEmail(eMail)){
			result=  true;
		}
		return result;
	}

	
	//Common Feedback
		
	@Override
	public List<CommonFeedback> getAllCommonFeedbackByProductId(int productId) {
		return commonFeedbackRepository.findAllByProductId(productId);
	}
	

	@Override
	public CommonFeedback getCommonFeedbackById(int feedbackId) {
		return commonFeedbackRepository.findById(feedbackId).get();
	}
	
	@Override
	public List<CommonFeedback> getAllCommonFeedbackByUserId(int userId) {
		return commonFeedbackRepository.findAllByUserId(userId);
	}

//	@Override
//	public int forwardRequestToMerchant(int feedbackId) {
//		int merchantId = 0;
//		CommonFeedback cfd =  getCommonFeedbackById(feedbackId);
//		if(cfd.isRequestFlag()== true) {
//			cfd.setRequestApproved(true);
//			Product product= productRepository.findByProductId(cfd.getProductId());
//			merchantId = product.getProductMerchantId();
//		}
//		return merchantId;
//	}

	@Override
	public String forwardResponseToCustomer(int feedbackId) {
		CommonFeedback cfd =  getCommonFeedbackById(feedbackId);
		if(cfd.isResponseFlag() == true) {
			cfd.setResponseApproved(true);
		}
		return cfd.getResponseMessage();
	}
	
	@Override
	public List<CommonFeedback> getAll() {
		return commonFeedbackRepository.findAll();
	}

	
	//Coupons:
	

}
