package com.example.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.main.controller.AdminController;
import com.example.main.exception.UserAlreadyExistsException;
import com.example.main.model.Coupon;
import com.example.main.model.MerchantDetails;
import com.example.main.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
class ControllerTests {
	
	@Autowired
	private AdminController admincontroller;
	
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("All test cases for Controller layer starting...");
	}
	
	@BeforeEach
	void setup() {
		System.out.println("Test Case Started");
	}

	@AfterEach
	void tearDown() {
		System.out.println("Test Case Over");	
	}
	
	@AfterAll
	static void setUpAfterClass() {
		System.out.println("All test cases ended.");
	}

	@Test
	@Rollback(true)
	public void testgetAllCustomers() {
		assertNotNull(admincontroller.getAllCustomers());
	}
	
	@Test
	@Rollback(true)
	public void testCreateMerchant() {
		MerchantDetails m = new MerchantDetails();
		m.setActive(true);
		//m.setAddresses("California");
		m.setAlternateEmail("jackson_123@gmail.com");
		m.setAlternatePhoneNumber("8946454278");
		m.setApproved(true);
		m.seteMail("jackson@gmail.com");
		m.setName("Jackson Mills");
		m.setPassword("jackson*123");
		m.setPhoneNumber("7453749330");
		m.setRating(4);
		m.setRole("MERCHANT_ROLE");
		m.setSecurityAnswer("California");
		m.setSecurityQuestion("Birth Place");
		m.setUserId(1);
		m.setUsername("Jack123");
		
		try {
			assertNotNull(admincontroller.registerMerchant(m));
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(true)
	public void testAddProduct() {
		Product p = new Product();
		p.setDiscount(0);
		p.setFeatured(true);
		p.setNoOfProducts(10);
		p.setNoOfProductViewed(5);
		p.setProductActivated(true);
		p.setProductBrand("Adidas");
		p.setProductCategory("Men's Fashion");
		p.setProductId(42729);
		p.setProductImage("shoes.jpg");
		p.setProductInfo("white and blue sports shoes");
		p.setProductName("Adidas cosia");
		p.setProductPrice(1783);
		p.setProductRating(4);
		p.setStatus(true);

			assertNotNull(admincontroller.addProduct(p));
	}
	
	@Test
	@Rollback(true)
	public void testgetAllCoupons() {
		assertNotNull(admincontroller.getAllCoupons());
	}
	
	@Test
	@Rollback(true)
	public void testAddCoupon() {
		Coupon c = new Coupon();
		c.setCouponAmount(250);
		c.setCouponCode("NewUser123");
		c.setCouponEndDate(null);
		c.setCouponId(456);
		c.setCouponMinOrderAmount(1000);
		c.setCouponStartDate(null);
		c.setIssuedBy("Admin");
		c.setUseId(42729);
			try {
				assertNotNull(admincontroller.addCoupon(c));
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	@Rollback(true)
	public void testgetAllCommonFeedback() {
		assertNotNull(admincontroller.getAll());
	}
}