package com.example.microservices.services.web;

import java.util.Arrays;
import java.util.List;

import javax.xml.ws.RespectBindingFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WebAccountsService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	
	protected Logger logger = LoggerFactory.getLogger(WebAccountsService.class);
	
	public WebAccountsService(String serviceUrl) {
		
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl :
			            "http://" + serviceUrl;
	}
	
	
	public Account findByNumber(String accountNumber) {

		logger.info("findByNumber() invoked: for " + accountNumber);
		
		// first argumen url
		// second argument response type
		// third argument path variables
		return restTemplate.getForObject(serviceUrl + "/accounts/{number}", Account.class, accountNumber);
	}
	

	public List<Account> byOwnerContains(String name) {

		logger.info("byOwnerContains() invoked: for " + name);
		
		Account[] accounts = null;

		try {
			
			// first argumen url
			// second argument response type
			// third argument path variables
			accounts = restTemplate.getForObject(serviceUrl + "/accounts/owner/{name}",Account[].class, name);

		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (accounts == null || accounts.length == 0)
			return null;
		else
			return Arrays.asList(accounts);
		
	}
}
