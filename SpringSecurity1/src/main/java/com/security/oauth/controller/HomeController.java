package com.security.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "hello";
	}
	
	@GetMapping("/private")
	public String privateArea() {
		return "private";
	}
}
