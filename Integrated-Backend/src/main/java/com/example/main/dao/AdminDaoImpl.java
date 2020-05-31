//package com.example.main.dao;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import com.example.main.model.CommonFeedback;
//import com.example.main.model.CustomerDetails;
//import com.example.main.model.MerchantDetails;
//import com.example.main.model.Product;
//import com.example.main.repository.CommonFeedbackRepository;
//import com.example.main.repository.CouponRepository;
//import com.example.main.repository.CustomerRepository;
//import com.example.main.repository.MerchantRepository;
//import com.example.main.repository.ProductFeedbackRepository;
//import com.example.main.repository.ProductRepository;
//
//
//
//@Component("AdminComponent")
//public class AdminDaoImpl implements AdminDao{
//
//	
//	@Autowired
//	@Qualifier(value="MerchantRepository")
//	private MerchantRepository merchantRepository;
//	
//	@Autowired
//	@Qualifier(value="CustomerRepository")
//	private CustomerRepository customerRepository;
//	
//	@Autowired
//	@Qualifier(value="ProductRepository")
//	private ProductRepository productRepository;
//	
//	@Autowired
//	@Qualifier(value="ProductFeedbackRepository")
//	private ProductFeedbackRepository productFeedbackRepository;
//	
//	@Autowired
//	@Qualifier(value="CommonFeedbackRepository")
//	private CommonFeedbackRepository commonFeedbackRepository;
//	
//	@Autowired
//	@Qualifier(value="CouponRepository")
//	private CouponRepository couponRepository;
//	
//	
//	
//	
//	
//	
//	
//	//Merchant:
//	
//	@Override
//	public void removeMerchantById(int userId) {
//		merchantRepository.deleteById(userId);
//		
//	}
//
//	@Override
//	public MerchantDetails addMerchant(MerchantDetails merchant) {
//		merchantRepository.save(merchant);
//		return merchant;
//	}
//
//	@Override
//	public MerchantDetails findMerchantById(int userId) {
//		return merchantRepository.findById(userId).get();
//	}
//
//	@Override
//	public MerchantDetails findMerchantByName(String name) {
//		return merchantRepository.findByName(name);
//	}
//	
//	@Override
//	public MerchantDetails findMerchantByeMail(String eMail) {
//		return merchantRepository.findByEmail(eMail);
//	}
//
//	
//	@Override
//	public Iterable<MerchantDetails> findAllMerchants() {
//		return  merchantRepository.findAll();
//	}
//	
//	@Override
//	public boolean updateMerchant(MerchantDetails updatedMerchant) {
//		boolean exists = merchantRepository.existsById(updatedMerchant.getUserId());
//		if(exists==true) {
//		MerchantDetails merchant = findMerchantById(updatedMerchant.getUserId());
//		if(updatedMerchant.getPhoneNumber()!=null)
//			merchant.setPhoneNumber(updatedMerchant.getPhoneNumber());
//		if(updatedMerchant.getAlternatePhoneNumber()!=null)
//			merchant.setAlternatePhoneNumber(updatedMerchant.getAlternatePhoneNumber());
//		if(updatedMerchant.getAddresses()!=null)
//			merchant.setAddresses(updatedMerchant.getAddresses());
//		if(updatedMerchant.getAlternateEmail()!=null)
//			merchant.setAlternateEmail(updatedMerchant.getAlternateEmail());
//		if(updatedMerchant.getName()!=null)
//			merchant.setName(updatedMerchant.getName());
//		merchantRepository.save(merchant);
//		return true;
//		}
//		return false;
//	}
//	
//	//Product
//	
//	@Override
//	public Product addProduct(Product product) {
//		productRepository.save(product);
//		return product;
//	}
//	
//	@Override
//	public boolean removeProduct(int productId) {
//		boolean exists = productRepository.existsById(productId);
//		if(exists==true) {
//		productRepository.deleteById(productId);
//		return true;
//		}
//		else
//			return false;
//	}
//	
//	@Override
//	public List<Product> getAllProducts() {
//		return productRepository.findAll();
//	}
//	
//	@Override
//	public Product getProductByProductId(int productId) {
//		return productRepository.findById(productId).get();
//	}
//	
//	@Override
//	public boolean update(Product product) {
//		boolean exists = productRepository.existsById(product.getProductId());
//		if(exists==true) {
//		Product p = productRepository.findById(product.getProductId()).get();
//		p.setFeatured(product.isFeatured());
//		if(product.getNoOfProducts()!=0)
//		p.setNoOfProducts(product.getNoOfProducts());
//		if(product.getNoOfProductViewed()!=0)
//		p.setNoOfProductViewed(product.getNoOfProductViewed());
//		p.setProductActivated(product.isProductActivated());
//		if(product.getProductBrand()!=null)
//		p.setProductBrand(product.getProductBrand());
//		if(product.getProductCategory()!=null)
//		p.setProductCategory(product.getProductCategory());
//		if(product.getProductImage()!=null)
//		p.setProductImage(product.getProductImage());
//		if(product.getProductInfo()!=null)
//		p.setProductInfo(product.getProductInfo());
//		if(product.getProductName()!=null)
//		p.setProductName(product.getProductName());
//		if(product.getProductPrice()!=0)
//		p.setProductPrice(product.getProductPrice());
//		if(product.getProductRating()!=0)
//		p.setProductRating(product.getProductRating());
//		p.setStatus(product.isStatus());
//		productRepository.save(p);
//		return true;
//		}
//		return false;
//	}
//
//	/**
//	 * Changes the name of a category. All the products having that category gets changed to another category
//	*/
//	@Override
//	public boolean updateCategoryByCategory(String productCategory, String updatedCategory) {
//		List<Product> products = productRepository.findAllByProductCategory(productCategory);
//		Iterator<Product> i = products.iterator();
//		while(i.hasNext()) {
//			Product pr= (Product)i.next();
//			pr.setProductCategory(updatedCategory);
//		}
//		productRepository.saveAll(products);
//		return true;
//	}
//
//// 	@Override
//// 	public boolean updateCategoryById(int productId, String updatedCategory) {
//// 		Product p = productRepository.findById(productId).get();
//// 		p.setProductCategory(updatedCategory);
//// 		productRepository.save(p);
//// 		return true;
//// 	}
//
//	@Override
//	public List<Product> getFeaturedProducts() {
//		List<Product> products= getAllProducts();
//		Iterator<Product> i = products.iterator();
//		while(i.hasNext()) {
//			Product pr= (Product)i.next();
//			if(pr.isFeatured()==false) {
//				i.remove();
//			}
//		}
//		return products;
//	}
//
//		
//	//Customer
//	
//	@Override
//	public void removeCustomerById(int userId) {
//		customerRepository.deleteById(userId);
//	}
//
//
//	@Override
//	public CustomerDetails findCustomerById(int userId) {
//		return customerRepository.findById(userId).get();
//	}
//
//	@Override
//	public CustomerDetails findCustomerByName(String name) {
//		return customerRepository.findByName(name);
//	}
//	
//	@Override
//	public CustomerDetails findCustomerByEMailIgnoreCase(String eMail) {
//		return customerRepository.findByEmail(eMail);
//	}
//
//	
//	@Override
//	public List<CustomerDetails> getAllCustomers() {
//		return customerRepository.findAll();
//	}
//	
//	@Override
//	public boolean existingCustomerByEmail(String eMail) {
//		boolean result = false;
//		if (customerRepository.existsByEmail(eMail)){
//			result=  true;
//		}
//		return result;
//	}
//
//	
//	//Common Feedback
//		
//	@Override
//	public List<CommonFeedback> getAllCommonFeedbackByProductId(int productId) {
//		return commonFeedbackRepository.findAllByProductId(productId);
//	}
//	
//
//	@Override
//	public CommonFeedback getCommonFeedbackById(int feedbackId) {
//		return commonFeedbackRepository.findById(feedbackId).get();
//	}
//	
//	@Override
//	public List<CommonFeedback> getAllCommonFeedbackByUserId(int userId) {
//		return commonFeedbackRepository.findAllByUserId(userId);
//	}
//
////	@Override
////	public int forwardRequestToMerchant(int feedbackId) {
////		int merchantId = 0;
////		CommonFeedback cfd =  getCommonFeedbackById(feedbackId);
////		if(cfd.isRequestFlag()== true) {
////			cfd.setRequestApproved(true);
////			Product product= productRepository.findByProductId(cfd.getProductId());
////			merchantId = product.getProductMerchantId();
////		}
////		return merchantId;
////	}
//
//	@Override
//	public String forwardResponseToCustomer(int feedbackId) {
//		CommonFeedback cfd =  getCommonFeedbackById(feedbackId);
//		if(cfd.isResponseFlag() == true) {
//			cfd.setResponseApproved(true);
//		}
//		return cfd.getResponseMessage();
//	}
//	
//	@Override
//	public List<CommonFeedback> getAll() {
//		return commonFeedbackRepository.findAll();
//	}
//
//	
//	//Coupons:
//	
//
//}

package com.example.main.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.main.email_token.ConfirmationToken;
import com.example.main.email_token.EmailSenderService;
import com.example.main.exception.UserAlreadyExistsException;
import com.example.main.model.CommonFeedback;
import com.example.main.model.Coupon;
import com.example.main.model.CustomerDetails;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;
import com.example.main.repository.CommonFeedbackRepository;
import com.example.main.repository.ConfirmationTokenRepository;
import com.example.main.repository.CouponRepository;
import com.example.main.repository.CustomerRepository;
import com.example.main.repository.MerchantRepository;
import com.example.main.repository.ProductFeedbackRepository;
import com.example.main.repository.ProductRepository;
import com.example.main.repository.UserRepository;



@Component("AdminComponent")
public class AdminDaoImpl implements AdminDao{

	org.slf4j.Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);
	
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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private CouponRepository couponRepo;
	//Merchant:
	
	@Override
	public void removeMerchantById(int userId) {
		logger.trace("Delete Merchant working...");
		merchantRepository.deleteById(userId);
		
	}

	@Override
	public void addMerchant(MerchantDetails merchant) throws MessagingException, UserAlreadyExistsException {
		logger.trace("Add Merchant working...");
		//merchantRepository.save(merchant);
		
		//added//
		
		MerchantDetails existingMerchant = userRepository.findMerchantByEmailIgnoreCase(merchant.getEmail());
        if(existingMerchant != null)
        {
            //return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        	throw new UserAlreadyExistsException("User exists with this email id!!!");
        }
        else
        {
            userRepository.saveMerchant(merchant);
            MerchantDetails md1=userRepository.findMerchantByEmailIgnoreCase(merchant.getEmail());

            ConfirmationToken confirmationToken = new ConfirmationToken(md1.getUserId());

            confirmationTokenRepository.save(confirmationToken);
            
            MimeMessage mailMessage = emailSenderService.createMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
            String url = "http://localhost:4200/verifyMerchant?token="+confirmationToken.getConfirmationToken();
            helper.setTo("dsonaje6@gmail.com");
            helper.setSubject("Merchant Requesting Approval!");
            helper.setFrom("capstore06@gmail.com");
            helper.setText("<html><body style='border-style: solid;\r\n" + 
            		"  border-color: #DCDCDC; background-color: #F0FFFF; height: 250px; width:500px; margin-left:250px'>"
            		+ "<h1>Merchant Registration!</h1><br>" +
            		merchant+"<br><button type='submit' autofocus style='margin-left:220px; border-radius: 9px; border: 2px solid #DCDCDC'>"
            		+"<a href="+url+">Show Details</a></button>",true);

            emailSenderService.sendEmail(mailMessage);
        }  
	}

	@Override
	public MerchantDetails findMerchantById(int userId) {
		logger.trace("Find Merchant By Id working...");
		return merchantRepository.findById(userId).get();
	}

	@Override
	public MerchantDetails findMerchantByName(String name) {
		logger.trace("Find Merchant By Name working...");
		return merchantRepository.findByName(name);
	}
	
	@Override
	public MerchantDetails findMerchantByeMail(String eMail) {
		logger.trace("Find Merchant By Email working...");
		return merchantRepository.findByEmail(eMail);
	}

	
	@Override
	public Iterable<MerchantDetails> findAllMerchants() {
		logger.trace("Find All Merchants working...");
		return  merchantRepository.findAll();
	}
	
	@Override
	public boolean updateMerchant(MerchantDetails updatedMerchant) {
		logger.trace("Update Merchant working...");
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
	
	@Override
	public MerchantDetails userLogin(String confToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confToken);
    	MerchantDetails merchant=userRepository.findMerchantById(token.getUid());
		return merchant;
	}
	
	@Override
	public boolean confirmAccount(String confirmationToken){
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            if(userRepository.findCustomerById(token.getUid())!=null) {
            	CustomerDetails cd=userRepository.findCustomerById(token.getUid());
            	cd.setActive(true);
                userRepository.saveCustomer(cd);
            }
       
            return true;
        }
        else
        	return false;
	}
	
	@Override
	public MerchantDetails generateToken(String confirmationToken, String action) throws MessagingException {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    	
    	MerchantDetails md=userRepository.findMerchantById(token.getUid());
    	if(action.equals("Accept")) {
    	md.setActive(true);
    	md.setApproved(true); }
    	else {
    	md.setActive(false);
    	md.setApproved(false);  }
    	
        userRepository.saveMerchant(md);
        
        MimeMessage mailMessage = emailSenderService.createMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setTo(md.getEmail());
        helper.setSubject("Account Activated!");
        helper.setFrom("capstore06@gmail.com");
        
        helper.setText("<html><body style='border-style: solid;\r\n" + 
        		"  border-color: #DCDCDC; background-color: #F0FFFF; height: 250px; width:500px; margin-left:250px'>"
        		+ "<h1>Admin approved your account!</h1><br>To login and access your account, please click here : <br>" +
        		"http://localhost:4200",true);

        emailSenderService.sendEmail(mailMessage);
        return md;
	}
	
	//Product
	
	@Override
	public Product addProduct(Product product) {
		logger.trace("Add Product working...");
		productRepository.save(product);
		return product;
	}
	
	@Override
	public boolean removeProduct(int productId) {
		logger.trace("Delete Product working...");
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
		logger.trace("Get All Products working...");
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductByProductId(int productId) {
		logger.trace("Get Product By Id working...");
		return productRepository.findById(productId).get();
	}
	
	@Override
	public boolean update(Product product) {
		logger.trace("Update Product working...");
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
		logger.trace("Update Category working...");
		List<Product> products = productRepository.findAllByProductCategory(productCategory);
		Iterator<Product> i = products.iterator();
		while(i.hasNext()) {
			Product pr= (Product)i.next();
			pr.setProductCategory(updatedCategory);
		}
		productRepository.saveAll(products);
		return true;
	}

		
	//Customer
	
	@Override
	public void removeCustomerById(int userId) {
		logger.trace("Remove Customer working...");
		customerRepository.deleteById(userId);
	}


	@Override
	public CustomerDetails findCustomerById(int userId) {
		logger.trace("Find Customer By Id working...");
		return customerRepository.findById(userId).get();
	}

	@Override
	public CustomerDetails findCustomerByName(String name) {
		logger.trace("Find Customer By Name working...");
		return customerRepository.findByName(name);
	}
	
	@Override
	public CustomerDetails findCustomerByEMailIgnoreCase(String eMail) {
		logger.trace("Find Customer By Email working...");
		return customerRepository.findByEmail(eMail);
	}

	
	@Override
	public List<CustomerDetails> getAllCustomers() {
		logger.trace("Get All Customers working...");
		return customerRepository.findAll();
	}
	
	@Override
	public boolean existingCustomerByEmail(String eMail) {
		logger.trace("Existing Customer By Email working...");
		boolean result = false;
		if (customerRepository.existsByEmail(eMail)){
			result=  true;
		}
		return result;
	}

	
	
	
	//Common Feedback
		
	@Override
	public List<CommonFeedback> getAllCommonFeedbackByProductId(int productId) {
		logger.trace("Get All Common Feedback By Product Id working...");
		return commonFeedbackRepository.findAllByProductId(productId);
	}
	

	@Override
	public CommonFeedback getCommonFeedbackById(int feedbackId) {
		logger.trace("Get Common Feedback By Id working...");
		return commonFeedbackRepository.findById(feedbackId).get();
	}
	
	@Override
	public List<CommonFeedback> getAllCommonFeedbackByUserId(int userId) {
		logger.trace("Get All Common Feedback By User Id working...");
		return commonFeedbackRepository.findAllByUserId(userId);
	}

	@Override
	public int forwardRequestToMerchant(int feedbackId) {
		logger.trace("Request to Merchant working...");
		int merchantId = 0;
		CommonFeedback cfd =  getCommonFeedbackById(feedbackId);
		if(cfd.isRequestFlag()== true) {
			cfd.setRequestApproved(true);
			merchantId = productRepository.findMerchantId(cfd.getProductId());
		}
		return merchantId;
	}

	@Override
	public String forwardResponseToCustomer(int feedbackId) {
		logger.trace("Forward Response To Customer working...");
		CommonFeedback cfd =  getCommonFeedbackById(feedbackId);
		if(cfd.isResponseFlag() == true) {
			cfd.setResponseApproved(true);
		}
		return cfd.getResponseMessage();
	}
	
	@Override
	public List<CommonFeedback> getAll() {
		logger.trace("Get All Common Feedback working...");
		return commonFeedbackRepository.findAll();
	}

	@Override
	public List<Coupon> getAllCoupons() {
		List<Coupon> coupon = new ArrayList<>();
		couponRepo.findAll().forEach(coupon::add);
		return coupon;
	}

	@Override
	public Coupon getCouponById(int couponId) throws Exception {
		Coupon coupon = couponRepo.findById(couponId)
				.orElseThrow(() -> new Exception("Coupon not found for this id : " + couponId));
		return coupon;
	}

	@Override
	public Coupon getCouponByCode(String couponCode) {
		Coupon coupon = couponRepo.findByCouponCode(couponCode);
		return coupon;
	}

	@Override
	public void addCoupon(@Valid Coupon coupon) throws MessagingException {
		couponRepo.save(coupon);
        
			long cnt=merchantRepository.count();
			List <MerchantDetails> merchants=merchantRepository.findAll();
        MimeMessage mailMessage = emailSenderService.createMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        String url = "http://localhost:4200/applyCoupon/"+coupon.getCouponId();
        
       
        	for(MerchantDetails mer:merchants) {
        		helper.setTo(mer.getEmail());
        		System.out.println(mer.getEmail());
        		helper.setSubject("Coupon Creation Approval!");
                
                helper.setText("<html><body style='border-style: solid;\r\n" + 
	            		"  border-color: #DCDCDC; background-color: #F0FFFF; height: 250px; width:500px; margin-left:250px'>"
	            		+ "<h1>Coupon Registration!</h1><br>" +
	            		coupon+"<br><button type='submit' autofocus style='margin-left:220px; border-radius: 9px; border: 2px solid #DCDCDC'>"
	            		+"<a href="+url+"/"+mer.getUserId()+">Show Details</a></button>",true);

                
                emailSenderService.sendEmail(mailMessage);
        	}
	}

	@Override
	public Coupon generateCoupon(int couponId, int id) throws Exception {
		Coupon coupon = couponRepo.findById(couponId)
    			.orElseThrow(() -> new Exception("Coupon not found for this id : " + couponId));
    	
        ConfirmationToken confirmationToken = new ConfirmationToken(coupon.getUserId());
       confirmationTokenRepository.save(confirmationToken);
        
        MerchantDetails merchant = merchantRepository.findById(id)
        		.orElseThrow(()->new Exception("Merchant not found for this id : " + coupon.getUserId()));
        
        coupon.setApproved(true);
        coupon.setUseId(id);
        couponRepo.save(coupon);
        merchantRepository.save(merchant);
        
        MimeMessage mailMessage = emailSenderService.createMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        String url = "http://localhost:4200/sendCoupon/"+coupon.getCouponId();
        helper.setTo("dsonaje6@gmail.com");
        helper.setSubject("Coupon Accepted!");
        helper.setFrom("capstore06@gmail.com");
        helper.setText("Merchant accepted coupon offer: "+coupon+"\nTo send this offer, Please click here : "
        +"\n"+url);

        emailSenderService.sendEmail(mailMessage);
		return coupon;
	}

	@Override
	public Coupon sendCoupon(int couponId) throws Exception {
		Coupon coupon = couponRepo.findById(couponId)
    			.orElseThrow(() -> new Exception("Coupon not found for this id : " + couponId));
        
        MimeMessage mailMessage = emailSenderService.createMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        String url = "http://localhost:4200/productpage/";
        
        List <MerchantDetails> merchants = merchantRepository.findAll();
        
        for(MerchantDetails mer:merchants) {
    		helper.setTo(mer.getEmail());
    		helper.setSubject("Latest Offers!!!");
            helper.setFrom("capstore06@gmail.com");
            helper.setText("Current Offers: "+coupon+"\nGrab this offer, Please click here : "
            +"\n"+url);

            emailSenderService.sendEmail(mailMessage);
    	}
        return coupon;
	}

	@Override
	public Boolean deleteCoupon(int couponId) {
		boolean exists = couponRepo.existsById(couponId);
		couponRepo.deleteById(couponId);
		return true;
	}
	
}

