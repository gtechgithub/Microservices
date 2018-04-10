package com.example.microservices.accounts;

import org.springframework.web.bind.annotation.RequestMapping;

public class Controller {

	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}
}
