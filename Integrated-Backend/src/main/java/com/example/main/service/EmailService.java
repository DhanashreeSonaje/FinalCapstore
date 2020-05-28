package com.example.main.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.main.dao.AdminDao;
import com.example.main.exception.UserAlreadyExistsException;
import com.example.main.model.MerchantDetails;
import com.example.main.model.User;

@Service("EmailService")
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private AdminDao adminDao;
	
	
	
	SimpleMailMessage mail = new SimpleMailMessage();
	
	//when new products are added in the store, customers receive notification--->logic not written
	public void sendInvitationsToUsers(User user) throws MailException{
		
		mail.setTo(user.getEmail());
		mail.setSubject("");
		
	}
	
	
    public void sendNotificationToMerchant(MerchantDetails merchant) throws MailException, MessagingException,
                                               UnsupportedEncodingException, UserAlreadyExistsException{
		
		MerchantDetails exist = adminDao.findMerchantByeMail(merchant.getEmail());
		
		if(exist==null) {
		    
			adminDao.addMerchant(merchant);
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(merchant.getEmail());
			
			String url = "http://localhost:4200/verifyMerchant/"+merchant.getEmail();
			
			helper.setSubject("New Merchant Request");
			  helper.setText("<html><body><h1>Merchant Registration!</h1><br>" +
			  merchant.getName()+"<br><button type='submit'>"
			  		+ "<a href="+url+">Show Details</a></button>" + "<br><body></html>", true);
			  
			  javaMailSender.send(message);
		}
		else {
			throw new UserAlreadyExistsException("Merchant exists with this email id!!!");
		}
	}
	
	
	

}
