package com.example.microservices.services.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.microservices.accounts.AccountRepository;
import com.example.microservices.accounts.AccountsController;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountsServer {


	//@Autowired
	//protected AccountRepository accountRepository;
	
	public static void main(String ...args) {
		System.setProperty("spring.config.name","accounts-server");
		SpringApplication.run(AccountsServer.class, args);
	}

	@Bean
	public AccountRepository getServerRepo() {
		return new AccountRepository();
	}
	
	@Bean
	public  AccountsController getaccountsController() {
		return new AccountsController(getServerRepo());
	}
}
