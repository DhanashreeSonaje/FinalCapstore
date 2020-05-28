package com.example.main.model;

import javax.persistence.*;

@Entity
@Table(name = "common_feedback")
public class CommonFeedback  {

	@Id
	@Column(name = "feedback_id")
	private int feedbackId;  //(Primary Key)
	@Column(name = "feedback_subject")
    private String feedbackSubject;
	@Column(name = "feedback_message")
    private String feedbackMessage;
	@Column(name= "product_id")
	private int productId;
	@Column(name="request_flag")
	private boolean requestFlag; 
	@Column(name="response_flag")
	private boolean responseFlag;
	@Column(name="request_approved")
	private boolean requestApproved;
	@Column(name="response_approved")
	private boolean responseApproved;
	@Column(name="response_message")
	private String responseMessage;
	@Column(name = "user_id")
	private int userId;
	
    
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public boolean isRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(boolean requestFlag) {
		this.requestFlag = requestFlag;
	}
	public boolean isResponseFlag() {
		return responseFlag;
	}
	public void setResponseFlag(boolean responseFlag) {
		this.responseFlag = responseFlag;
	}
	public boolean isRequestApproved() {
		return requestApproved;
	}
	public void setRequestApproved(boolean requestApproved) {
		this.requestApproved = requestApproved;
	}
	public boolean isResponseApproved() {
		return responseApproved;
	}
	public void setResponseApproved(boolean responseApproved) {
		this.responseApproved = responseApproved;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getFeedbackSubject() {
		return feedbackSubject;
	}
	public void setFeedbackSubject(String feedbackSubject) {
		this.feedbackSubject = feedbackSubject;
	}
	public String getFeedbackMessage() {
		return feedbackMessage;
	}
	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public CommonFeedback() {
	}
}