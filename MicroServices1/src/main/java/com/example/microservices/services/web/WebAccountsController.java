package com.example.microservices.services.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebAccountsController {

	@Autowired
	protected WebAccountsService accountsService;

	protected Logger logger = LoggerFactory.getLogger(WebAccountsController.class);
		
	public WebAccountsController(WebAccountsService accountsService) {
		this.accountsService = accountsService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder bind){
		bind.setAllowedFields("accountNumber","searchText");
	}
	

	@RequestMapping("/accounts/{accountNumber}")
	public ModelAndView byNumber(@PathVariable("accountNumber") String accountNumber) {

		logger.info("web-service byNumber() invoked: " + accountNumber);
		Account account = accountsService.findByNumber(accountNumber);
		logger.info("web-service byNumber() found: " + account);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("account");
		view.addObject("account", account);

		return view;
	}
	

	@RequestMapping("/accounts/owner/{text}")
	public ModelAndView ownerSearch(@PathVariable("text") String name) {
		logger.info("web-service byOwner() invoked: " + name);

		List<Account> accounts = accountsService.byOwnerContains(name);
		logger.info("web-service byOwner() found: " + accounts);

		ModelAndView view = new ModelAndView();
		view.setViewName("accounts");

		if (accounts != null)
			view.addObject("accounts", accounts);
		
		return view;
	}
}
