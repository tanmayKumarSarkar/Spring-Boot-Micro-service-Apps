package com.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.oauth.entities.CustomUserDetails;
import com.security.oauth.repositories.UserRepository;

@SpringBootApplication
public class SpringSo6Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSo6Application.class, args);
	}
	
//	@Autowired
//	@Bean
//	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
//		builder.userDetailsService(username -> new CustomUserDetails(repo.findByUserName(username)));
//	}
}
