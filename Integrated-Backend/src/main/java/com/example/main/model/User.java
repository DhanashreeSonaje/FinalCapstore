package com.example.main.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;  //( number auto generated): Primary Key
    private String name;
    private String username;  //(min: 3)
	private String password;    //(min: 8)
	private String email;
    private String role;           //(ROLE_CUSTOMER,ROLE_ADMIN,ROLE_MERCHANT)
    private boolean isActive;
	private String securityQuestion;
	private String securityAnswer;
	public User(int userId, String name, String username, String password, String email, String role, boolean isActive,
			String securityQuestion, String securityAnswer) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.isActive = isActive;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	public User() {
		super();
		this.isActive=false;
	}
	@Override
	public String toString() {
		return "User Id : " + userId + ",\nName : " + name + ",\n Email : " + email + ",\n Role : " + role;
	}
	
	
}