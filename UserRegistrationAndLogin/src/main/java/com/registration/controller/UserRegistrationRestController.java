package com.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.registration.entity.User;
import com.registration.service.UserRegistrationServiceImpl;
@CrossOrigin(value="${crossorigin}")
@RestController
public class UserRegistrationRestController {
	@Autowired
	private UserRegistrationServiceImpl service;
	
	
	@PostMapping(value="/register")
	public User registerUser(@RequestBody User user) throws Exception{
		String email = user.getEmail();
		User saveUser = service.saveUser(user);
		createOtp(saveUser);
		return  saveUser;
	}
	@GetMapping("/email/{email}")
	public String getEmail(@PathVariable String email) {
		User findByUserEmail = service.findByUserEmail(email);
		String email2 = findByUserEmail.getEmail();
		return email2;
	}
	
	@GetMapping("/getName/{email}")
	public String getName(@PathVariable String email) {
		User findByUserEmail = service.findByUserEmail(email);
		String name = findByUserEmail.getName();
		System.out.println(" username is "+ name);
		return name;
	}
	
	@PostMapping("/login")
	public ResponseEntity<User>  loginUser(@RequestBody User user) throws Exception {
		String email = user.getEmail();
		String password = user.getPassword();
		User users=null;
		
		if(email !=null && password !=null) {
			 users= service.fetchEmailAndPassword(email, password);		
			}	
		
		if(users==null) {
		throw new Exception("Bad Credentials");
	}
		return new ResponseEntity<User>(users,HttpStatus.OK);	
	}	
	
	
	@PostMapping("/createOtp")
	public String createOtp(@RequestBody User user) throws Exception
	{
		String email = user.getEmail();
		User findByUserEmail = service.findByUserEmail(email);
		return service.createOtp(findByUserEmail,email);
	}
	

  
	  
	  
	@GetMapping("/getOtp/{email}/{otp}")
	public Integer getOtp(@PathVariable String email,@PathVariable Integer otp) throws Exception {
		User findByUserEmail = service.findByUserEmail(email);
		return service.getOtp(findByUserEmail, email, otp);
	
	}


}
