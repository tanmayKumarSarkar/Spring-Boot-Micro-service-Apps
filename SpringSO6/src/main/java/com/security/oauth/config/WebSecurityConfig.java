package com.security.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.oauth.entities.CustomUserDetails;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
    private PasswordEncoder passwordEncoder;
	private UserDetailsService CustomUserDetails;  
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }   
	
	@Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(CustomUserDetails)
            .passwordEncoder(passwordEncoder);    
    }
	
	@Bean
	PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}

}