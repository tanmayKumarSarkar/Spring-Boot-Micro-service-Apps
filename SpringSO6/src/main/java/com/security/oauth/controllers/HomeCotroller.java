package com.security.oauth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeCotroller {

	@GetMapping("/")
	public String home () {
		return "Home";
	}
	
	@GetMapping("/private")
	public String privateHome () {
		return "Private";
	}
}
