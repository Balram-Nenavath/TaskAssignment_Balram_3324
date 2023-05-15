package com.registration.service;

import com.registration.entity.User;

public interface UserRegistrationService {
	
	public User saveUser(User user);
	
	public User fetchEmailAndPassword(String email,String password);

}
