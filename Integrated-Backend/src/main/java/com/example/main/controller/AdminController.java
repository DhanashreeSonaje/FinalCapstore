package com.example.main.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.email_token.ConfirmationToken;
import com.example.main.email_token.EmailSenderService;
import com.example.main.exception.UserAlreadyExistsException;
import com.example.main.model.CommonFeedback;
import com.example.main.model.Coupon;
import com.example.main.model.CustomerDetails;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;
import com.example.main.model.User;
import com.example.main.repository.ConfirmationTokenRepository;
import com.example.main.repository.CouponRepository;
import com.example.main.repository.MerchantRepository;
import com.example.main.repository.ProductRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.AdminService;
import com.example.main.service.EmailService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController("AdminController")
@RequestMapping(value="/capstore/admin",method= {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
public class AdminController {

			
		@Autowired
		private AdminService adminService;
		
		@Autowired
		private EmailService emailService;
		
		@Autowired
		private ProductRepository productRepository;
		
		
		@Autowired
		UserRepository userRepository;
		
		@Autowired 
		MerchantRepository merchantRepository;
		
		@Autowired
		CouponRepository couponRepo;
		
		@Autowired
		ConfirmationTokenRepository confirmationTokenRepository;
		
		@Autowired
		EmailSenderService emailSenderService;
		
		
		//Customer:
		
		@GetMapping("/getAllCustomers")
		public ResponseEntity<List<CustomerDetails>> getAllCustomers()
		   {
				List<CustomerDetails> customers= adminService.getAllCustomers();
				System.out.println("In Get All Customers");
				System.out.println(customers);
				if(customers.isEmpty()) {
					return new ResponseEntity("Sorry! No Customer Found!", HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<List<CustomerDetails>>(customers, HttpStatus.OK);
			}
		  
		@DeleteMapping("/deleteCustomer/{userId}")//Not working --->delete cascaded ones first
		public  String deleteCustomer(@PathVariable("userId")int userId) {
			 adminService.removeCustomerById(userId);
			 return "Account removed successfully!";
			}
		 
		 
		 
		 
		 
		 //merchant:
		 
		@RequestMapping(value="/registerMerchant", method = RequestMethod.POST)
	    public ResponseEntity<?> registerMerchant(@Valid @RequestBody MerchantDetails md) throws MessagingException
	    {

	        MerchantDetails existingMerchant = userRepository.findMerchantByEmailIgnoreCase(md.getEmail());
	        if(existingMerchant != null)
	        {
	            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
	        }
	        else
	        {
	            userRepository.saveMerchant(md);
	            MerchantDetails md1=userRepository.findMerchantByEmailIgnoreCase(md.getEmail());

	            ConfirmationToken confirmationToken = new ConfirmationToken(md1.getUserId());

	            confirmationTokenRepository.save(confirmationToken);
	            
	            MimeMessage mailMessage = emailSenderService.createMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
	            String url = "http://localhost:4200/verifyMerchant?token="+confirmationToken.getConfirmationToken();
	            helper.setTo("dsonaje6@gmail.com");
	            helper.setSubject("Merchant Requesting Approval!");
	            helper.setFrom("capstore06@gmail.com");
	            helper.setText("<html><body><h1>Merchant Registration!</h1><br>" +
	            		md+"<br><button type='submit' class='is-small btn btn-info'>"
	            		+"<a href="+url+">Show Details</a></button>",true);

	            emailSenderService.sendEmail(mailMessage);
	            
	            return ResponseEntity.ok(HttpStatus.OK);
	        }
	    }
	    
	    
	    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	    public ResponseEntity<?> confirmUserAccount(@Valid  @RequestParam("token") String confirmationToken)
	    {
	        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

	        if(token != null)
	        {
	            if(userRepository.findCustomerById(token.getUid())!=null) {
	            	CustomerDetails cd=userRepository.findCustomerById(token.getUid());
	            	cd.setActive(true);
	                userRepository.saveCustomer(cd);
	            }
	       
	            return ResponseEntity.ok(HttpStatus.OK);
	      }
	        else
	        {
	        	return new ResponseEntity<Error>(HttpStatus.CONFLICT);
	        }
	     }
	    
	    @GetMapping("/generateToken")
	    public ResponseEntity<?> generateToken(@Valid  @RequestParam("token") String confirmationToken,@Valid  @RequestParam("action") String action) throws MessagingException{
	    	
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
	        helper.setText("Admin approved your account.\nTo login and access your account, please click here : "
	        +"http://localhost:4200");

	        emailSenderService.sendEmail(mailMessage);
	        
	        return ResponseEntity.ok().body(md);
	    }
	    
	    @RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	    public ResponseEntity<?> userLogin(@Valid @RequestBody String[] userCredentials) {
	    	String email=userCredentials[0];
	    	String pass=userCredentials[1];
	    	String role=userCredentials[2];
	    	System.out.println(email+pass+role);
	    	if (role.equals("Customer")) {
	    		CustomerDetails cd=userRepository.findCustomerByEmailIgnoreCase(email);
	    		if(cd!=null && cd.isActive()==true) {
	    			if(pass.equals(cd.getPassword())) {
	    				return ResponseEntity.ok().body(cd);
	    			}
	    		}
	    	}
	    	else {
	    		MerchantDetails md=userRepository.findMerchantByEmailIgnoreCase(email);
	    		if(md!=null && md.isActive()==true) {
	    			if(pass.equals(md.getPassword())) {
	    				return ResponseEntity.ok().body(md);
	    			}
	    		}
	    	}
	    	return new ResponseEntity<Error>(HttpStatus.CONFLICT);
	    }
	    
	    @RequestMapping(value="/getMerchant", method= {RequestMethod.GET, RequestMethod.POST})
	    public ResponseEntity<?> userLogin(@Valid  @RequestParam("token") String confToken) {
	    	ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confToken);
	    	MerchantDetails md=userRepository.findMerchantById(token.getUid());
	    	return ResponseEntity.ok().body(md);
	    }
		
		 

		@GetMapping(value = "/findMerchantById/{userId}")
		public MerchantDetails getMerchant(@PathVariable("userId")Integer userId) {
			return adminService.findMerchantById(userId);
			
		}
		
		
		 @GetMapping("/getAllMerchants")
		 public ResponseEntity<List<MerchantDetails>> getAllMerchants(){
			List<MerchantDetails> merchants= adminService.getAllMerchant();
				if(merchants.isEmpty()) {
				return new ResponseEntity("Sorry! No Merchant Found!", HttpStatus.NOT_FOUND);
			}
					
				return new ResponseEntity<List<MerchantDetails>>(merchants, HttpStatus.OK);		
			}
		 
		@DeleteMapping("/deleteMerchant/{merchantId}")
		public  Map<String, Boolean> deleteMerchant(@PathVariable("merchantId")int merchantId) {
			 adminService.removeMerchantById(merchantId);
			 Map<String, Boolean> response = new HashMap<>();
			 response.put("deleted", Boolean.TRUE);
			 return response;
			}
		
		
		@RequestMapping(value ="/inviteUsers",method = { RequestMethod.GET,RequestMethod.POST })
		public void invite(User user){//Not written 
		     emailService.sendInvitationsToUsers(user);	
		}
		
		@PutMapping(value="/updateMerchant")
		public boolean update(@RequestBody MerchantDetails merchant) {
		     return adminService.updateMerchant(merchant);
		}
		
		//Product: 
		
		 @DeleteMapping("deleteProduct/{productID}")
		 public boolean DeleteProduct(@PathVariable("productID")int productID)
		 {
			 return adminService.removeProduct(productID);
		 }
		
		@PostMapping("/addProduct")
		public Product addProduct(@RequestBody Product product) {
			product.setProductId((int)(Math.random()*100000));
			product.setDiscount(0);
			return adminService.addProduct(product);
		}
		
		@GetMapping("/getAllProducts")
		List<Product> getAllProducts(){
			return adminService.getAllProducts();
		}
		
		@GetMapping("/getProductById/{productId}")
		Product getProductByProductId(@PathVariable int productId) {
			return adminService.getProductByProductId(productId);
		}
		
		@PutMapping("/updateProduct")
		boolean update(@RequestBody Product product) {
			return adminService.update(product);
		}
		
		@PutMapping("/updateCategoryByCategory")
		boolean updateCategoryByCategory(@RequestParam("productCategory")String productCategory, @RequestParam("updatedCategory")String updatedCategory) {
			return adminService.updateCategoryByCategory(productCategory, updatedCategory);
		}
			
	
		//Coupon
		
		
		@PostMapping(value = "/create")
		public ResponseEntity<Coupon> addCoupon(@Valid @RequestBody Coupon coupon) throws MessagingException {
		
//	  			couponRepo.save(coupon);
//	            
//	  			long cnt=merchantRepository.count();
//	  			List <MerchantDetails> merchants=merchantRepository.findAll();
//	            MimeMessage mailMessage = emailSenderService.createMessage();
//	            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
//	            String url = "http://localhost:4200/applyCoupon/"+coupon.getCouponId();
//	            
//	           
//	            	for(MerchantDetails mer:merchants) {
//	            		System.out.println("In for");
//	            		helper.setTo(mer.getEmail());
//	            		System.out.println(mer.getEmail());
//	            		helper.setSubject("Coupon Creation Approval!");
//	                    helper.setText("<html><body><h1>Coupon Registration!</h1><br>" +
//	              			  coupon+"<br><button type='submit' class='is-small btn btn-info'>"
//	        		  		+ "<a href="+url+"/"+mer.getUserId()+">Show Details</a></button>",true);
//	                    
//	                    emailSenderService.sendEmail(mailMessage);
//	            	}
			adminService.addCoupon(coupon);
	            
				return new ResponseEntity<Coupon>(HttpStatus.CREATED);
		}
	    
	    @PutMapping("/generateCoupon/{couponId}/{id}")
	    public Coupon generateCoupon(@PathVariable("couponId") int couponId, @PathVariable("id") int id) throws Exception{
//	    	Coupon coupon = couponRepo.findById(couponId)
//	    			.orElseThrow(() -> new Exception("Coupon not found for this id : " + couponId));
//	    	
//	        ConfirmationToken confirmationToken = new ConfirmationToken(coupon.getUserId());
//	       confirmationTokenRepository.save(confirmationToken);
//	        
//	        MerchantDetails merchant = merchantRepository.findById(id)
//	        		.orElseThrow(()->new Exception("Merchant not found for this id : " + coupon.getUserId()));
//	        
//	        coupon.setApproved(true);
//	        coupon.setUseId(id);
//	        couponRepo.save(coupon);
//	        merchantRepository.save(merchant);
//	        
//	        MimeMessage mailMessage = emailSenderService.createMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
//	        String url = "http://localhost:4200/sendCoupon/"+coupon.getCouponId();
//	        helper.setTo("dsonaje6@gmail.com");
//	        helper.setSubject("Coupon Accepted!");
//	        helper.setFrom("capstore06@gmail.com");
//	        helper.setText("Merchant accepted coupon offer: "+coupon+"\nTo send this offer, please click here : "
//	        +"\n"+url);
//
//	        emailSenderService.sendEmail(mailMessage);
	        
	        return adminService.generateCoupon(couponId, id);
	    }
	  
	    ///////////////////////////////new/////////////////////////////////
	    @PutMapping("/sendCoupon/{couponId}")
	    public Coupon sendCoupon(@PathVariable("couponId") int couponId) throws Exception{
//	    	Coupon coupon = couponRepo.findById(couponId)
//	    			.orElseThrow(() -> new Exception("Coupon not found for this id : " + couponId));
//	        
//	        MimeMessage mailMessage = emailSenderService.createMessage();
//	        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
//	        String url = "http://localhost:4200/productpage/";
//	        
//	        List <MerchantDetails> merchants = merchantRepository.findAll();
//	        
//	        for(MerchantDetails mer:merchants) {
//	    		helper.setTo(mer.getEmail());
//	    		helper.setSubject("Latest Offers!!!");
//	            helper.setFrom("capstore06@gmail.com");
//	            helper.setText("Current Offers: "+coupon+"\nGrab this offer, please click here : "
//	            +"\n"+url);
//
//	            emailSenderService.sendEmail(mailMessage);
//	    	}
	        return adminService.sendCoupon(couponId);
	    }
	    
	    ////////////////////////////////////////////////////////////////////////
	  	@GetMapping("/coupons")
		public ResponseEntity<List<Coupon>>getAllCoupons(){
//	  		List<Coupon> coupon = new ArrayList<>();
//			couponRepo.findAll().forEach(coupon::add);
			return new ResponseEntity<List<Coupon>>(adminService.getCoupons(),HttpStatus.OK);
		}
		
		@PutMapping("/Id/{couponId}")
		public ResponseEntity<Coupon> getCouponById(@PathVariable("couponId") int couponId) throws Exception{
//			Coupon coupon = couponRepo.findById(couponId)
//					.orElseThrow(() -> new Exception("Coupon not found for this id : " + couponId));
//		    
			return new ResponseEntity<Coupon>(adminService.getCouponById(couponId), HttpStatus.OK);
		}
		
		@PutMapping("/Code/{couponCode}")
		public ResponseEntity<Coupon> getCoupon(@PathVariable("couponCode") String couponCode){
			Coupon coupon = adminService.getCouponByCode(couponCode);
		     
			if(coupon==null) {
				return new ResponseEntity("Sorry! Coupon not found!",HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
		}
		
		@DeleteMapping("/coupon/{promocodeId}")
		public ResponseEntity<Boolean> deleteCoupon(@PathVariable("promocodeId") int couponId){
			System.out.println("Deleted1");
			adminService.deleteCoupon(couponId);
			System.out.println("Deleted2");
			return ResponseEntity.ok().body(true);
			
		}
		
		
		
	//
	@PutMapping("/addDiscount/{discount}/{productID}")
		public ResponseEntity<Boolean> addDiscount(@PathVariable("discount") int discount,@PathVariable("productID") int productID)
		{
			Product product=productRepository.findById(productID).get();
			product.setDiscount(discount);
			productRepository.save(product);
			return ResponseEntity.ok().body(true);
			
		}
	
	
	//Common Feedback:
		
//		@PutMapping(value="/forwardRequestToMerchant/{feedbackId}")
//		public int forwardRequestToMerchant(@PathVariable int feedbackId) {
//			return adminService.forwardRequestToMerchant(feedbackId);
//		}
		
		@GetMapping(value="/forwardResponseToCustomer/{feedbackId}")
		public String forwardResponseToCustomer(@PathVariable int feedbackId) {
			return adminService.forwardResponseToCustomer(feedbackId);
		}
		
		@GetMapping(value="/getAllCommonFeedbackByUserId/{userId}")
		public List<CommonFeedback> getAllCommonFeedbackByUserId(@PathVariable("userId") int userId) {
			return adminService.getAllCommonFeedbackByUserId(userId);
		}
		
		@GetMapping(value="/getCommonFeedbackById/{feedbackId}")
		public CommonFeedback getCommonFeedbackById(@PathVariable("feedbackId") int feedbackId) {
			return adminService.getCommonFeedbackById(feedbackId);
		}
		
		@GetMapping(value="/getAllCommonFeedbackByProductId/{productId}")
		public List<CommonFeedback> getAllCommonFeedbackByProductId(@PathVariable("productId") int productId) {
			return adminService.getAllCommonFeedbackByProductId(productId);
		}
		
		@GetMapping(value="/getAllCommonFeedback")
		public List<CommonFeedback> getAll() {
			return adminService.getAll();
		}
		
		 
	}