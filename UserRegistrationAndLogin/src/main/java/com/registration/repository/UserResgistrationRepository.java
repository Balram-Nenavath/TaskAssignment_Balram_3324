package com.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registration.entity.User;

public interface UserResgistrationRepository  extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);

	 public User findByEmailAndPassword(String email, String password);
	 
}
