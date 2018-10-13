package com.security.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.oauth.entity.User;
import com.security.oauth.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User findByUserName(String userName) {
		return userRepo.findByUserSOEId(userName);
	}
}
