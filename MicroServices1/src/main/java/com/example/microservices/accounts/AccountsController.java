package com.example.microservices.accounts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

	protected Logger logger = LoggerFactory.getLogger(AccountsController.class);
	
	@Autowired
	protected AccountRepository accountRepository;

	 /* Create an instance plugging in the respository of Accounts.
	 * 
	 * @param accountRepository
	 *            An account repository implementation.
	 */
	@Autowired
	public AccountsController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;

		logger.info("AccountRepository says system has "
				+ accountRepository.countAccounts() + " accounts");
	}

	/**
	 * Fetch an account with the specified account number.
	 * 
	 * @param accountNumber
	 *            A numeric, 9 digit account number.
	 * @return The account if found.
	 * @throws AccountNotFoundException
	 *             If the number is not recognised.
	 */	
	@RequestMapping("/accounts/{accountNumber}")
	public Account 	byNumber(@PathVariable("accountNumber") String accountNumber) {
		
		Account acc = null;
		
		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		acc = accountRepository.findByNumber(accountNumber);

		if(acc == null) {
		   throw new AccountNotFoundException("Account number not found");	
		}
		
		logger.info("accounts-service byNumber() found: " + accountNumber);
		
		return acc;
	}
	
	/**
	 * Fetch owner name based on the string passed as parameter
	 * 
	 * @param partialName
	 * @return List collection
	 * @throws if null throws AccountNotFoundException
	 */
	@RequestMapping("/accounts/owner/{name}")
	public List<Account> byOwner(@PathVariable("name") String partialName) {
		List<Account> accounts = null;

		logger.info("accounts-service byOwner() invoked: "
				+ accountRepository.getClass().getName() + " for "
				+ partialName);
		accounts = accountRepository.findyByOwnerContainingIgnoreCase(partialName);

		if (accounts == null || accounts.size() == 0) {
			throw new AccountNotFoundException("owner name " + partialName + " not found");
		}
		logger.info("accounts-service byOwner() found: " + accounts);
		
		return accounts;
	}
}