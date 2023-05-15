package com.registration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Registration_Details")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="User_Id")
	private Integer id;
	@Column(name = "User_EmailId")
	private String email;
	@Column(name = "User_Name")
	private String name;
	@Column(name = "User_Password")
	private String password;
	
	@Column( name = "otp")
	private Integer otp;
	
	public User() {
		
	}

	public User(Integer id, String email, String name, String password, Integer otp) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.otp=otp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", otp=" + otp + "]";
	}
	
	

}
