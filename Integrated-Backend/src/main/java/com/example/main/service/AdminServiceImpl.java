package com.example.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.main.dao.AdminDao;
import com.example.main.email_token.ConfirmationToken;
import com.example.main.email_token.EmailSenderService;
import com.example.main.model.CommonFeedback;
import com.example.main.model.Coupon;
import com.example.main.model.CustomerDetails;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;
import com.example.main.model.ProductFeedback;
import com.example.main.repository.ConfirmationTokenRepository;
import com.example.main.repository.CouponRepository;
import com.example.main.repository.MerchantRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	CouponRepository couponRepo;
	
	@Autowired
	EmailSenderService emailSenderService;

	//Merchant: 
	@Override
	public MerchantDetails addMerchant(MerchantDetails m) {
		return adminDao.addMerchant(m);
		
	}

	@Override
	public void removeMerchantById(int merchantId) {
		adminDao.removeMerchantById(merchantId);
	}
	

	@Override
	public List<MerchantDetails> getAllMerchant() {
		List<MerchantDetails> merchants=new ArrayList<MerchantDetails>();
		adminDao.findAllMerchants().forEach(merchants::add);
		return merchants;
	}

	@Override
	public boolean updateMerchant(MerchantDetails updatedMerchant) {
		return adminDao.updateMerchant(updatedMerchant);
	}

	@Override
	public MerchantDetails findMerchantById(int userId) {
		return adminDao.findMerchantById(userId);
	}
	
    
	
	public MerchantDetails verifyMerchantDetails(String email) {
		return adminDao.findMerchantByeMail(email);
	}


	public MerchantDetails getApproval(String email, boolean approved) {
		MerchantDetails merchant = adminDao.findMerchantByeMail(email);
		merchant.setApproved(approved);
		adminDao.addMerchant(merchant);
		return merchant;
	}
	
	
	public List<MerchantDetails> getMerchants() {
		List<MerchantDetails> merchant = new ArrayList<>();
		adminDao.findAllMerchants().forEach(merchant::add);
		return merchant;
	}

	
	
	//Product:
	@Override
	public Product addProduct(Product product) {
		return adminDao.addProduct(product);
	}

	@Override
	public boolean removeProduct(int productId) {
		return adminDao.removeProduct(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return adminDao.getAllProducts();
	}

	@Override
	public Product getProductByProductId(int productId) {
		return adminDao.getProductByProductId(productId);
	}

	@Override
	public boolean update(Product product) {
		return adminDao.update(product);
	}

	@Override
	public boolean updateCategoryByCategory(String productCategory, String updatedCategory) {
		return adminDao.updateCategoryByCategory(productCategory, updatedCategory);
	}
	
	
	//Customer:

	@Override
	public void removeCustomerById(int userId) {
		adminDao.removeCustomerById(userId);
	}


	@Override
	public CustomerDetails findCustomerById(int userId) {
		return adminDao.findCustomerById(userId);
	}

	@Override
	public CustomerDetails findCustomerByName(String name) {
		return adminDao.findCustomerByName(name);
	}

	@Override
	public List<CustomerDetails> getAllCustomers() {
		return adminDao.getAllCustomers();
	}

	
	
	
	
	//CommonFeedback:
	
	
	@Override
	public List<CommonFeedback> getAllCommonFeedbackByUserId(int userId) {
		return adminDao.getAllCommonFeedbackByUserId(userId);
	}

	@Override
	public CommonFeedback getCommonFeedbackById(int feedbackId) {
		return adminDao.getCommonFeedbackById(feedbackId);
	}
	
	@Override
	public List<CommonFeedback> getAllCommonFeedbackByProductId(int productId) {
		return adminDao.getAllCommonFeedbackByProductId(productId);
	}

//	@Override
//	public int forwardRequestToMerchant(int feedbackId) {
//		return adminDao.forwardRequestToMerchant(feedbackId);
//	}

	@Override
	public String forwardResponseToCustomer(int feedbackId) {
		return adminDao.forwardResponseToCustomer(feedbackId);
	}
	
	@Override
	public List<CommonFeedback> getAll() {
		return adminDao.getAll();
	}


	
	
	
	
	//ProductFeedback:
	

	@Override
	public void removeProductFeedbackkById(int feedbackId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProductFeedbackByUserId(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductFeedback> getAllProductFeedbackByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductFeedback getProductFeedbackById(int feedbackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductFeedback> getProductFeedbackByProductId(int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public ProductFeedback addCommonFeedback(ProductFeedback pfd) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Coupon:
	

	@Override
	public List<Coupon> getCoupons() {
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
                helper.setText("<html><body><h1>Coupon Registration!</h1><br>" +
          			  coupon+"<br><button type='submit' class='is-small btn btn-info'>"
    		  		+ "<a href="+url+"/"+mer.getUserId()+">Show Details</a></button>",true);
                
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
        helper.setText("Merchant accepted coupon offer: "+coupon+"\nTo send this offer, please click here : "
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
            helper.setText("Current Offers: "+coupon+"\nGrab this offer, please click here : "
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
