package com.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.registration.entity.User;
import com.registration.exception.EmailNotFoundException;
import com.registration.exception.InvalidOtpException;
import com.registration.repository.UserResgistrationRepository;
@Service
public class UserRegistrationServiceImpl  implements UserRegistrationService{
	
	@Value("${api-key}")
	String apikey;
	@Value("${api-host}")
	String apiHost;
	@Value("${api-url}")
	String apiUrl;
	

	Integer randNumber = (int) (Math.random()*10000);
	
	@Autowired
	private EmailConfig econfig;
	
	@Autowired
	private UserResgistrationRepository repo;
	@Override
	public User saveUser(User user) {
		User savedUser = repo.save(user);
		return savedUser;
	}
	
	public User findByUserEmail(String email) {
		User user = repo.findByEmail(email);
		return user;
		
	}

	@Override
	public User fetchEmailAndPassword(String email, String password)  {
		User fetchUserByEmailAndPassword = repo.findByEmailAndPassword(email, password);
		return fetchUserByEmailAndPassword;
		

	}

	public String sendEmail(String email, Integer otp) {

		RestTemplate restTemplate = new RestTemplate();
		String body = econfig.getStr1().toString() + email + econfig.getStr2().toString() + otp + econfig.getStr3().toString();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-RapidAPI-Key",apikey);
		headers.set("X-RapidAPI-Host", apiHost);

		HttpEntity<?> requestEntity = new HttpEntity<>(body,headers);

		String url=apiUrl;

		ResponseEntity<String> response = restTemplate.exchange(
				url, HttpMethod.POST, requestEntity, String.class);
		return response.getBody();
	}

	public Integer getOtp(User findByUserEmail, String email, Integer otp) {
		String email2 = findByUserEmail.getEmail();
		Integer dbOtp = findByUserEmail.getOtp();
		if(email2.equals(email)) {
			//randNumber = this.randNumber;
			if(otp.equals(dbOtp)) {
				return otp;
			}
			else {
				throw new InvalidOtpException("Invalid OTP");
			}
		}
		else
		{
			throw new EmailNotFoundException("Email not found in the Database, please check !");
		}
		
	}

	public String createOtp(User findByUserEmail, String email) {
		String response=null;
		String email2 = findByUserEmail.getEmail();
		if(email2.equals(email)) {
		findByUserEmail.setOtp(randNumber);
		saveUser(findByUserEmail);
		response = sendEmail(findByUserEmail.getEmail(), findByUserEmail.getOtp());
			}else
			{
				throw new EmailNotFoundException("Email not found in the Database, please check !");
			}
		return response;
		
	}
	
	
	
	
		
	}


