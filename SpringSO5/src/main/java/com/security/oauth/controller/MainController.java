package com.security.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/public/main")
public class MainController {

	@GetMapping("/hi")
	public String Hi() {return "Hi";}
}
