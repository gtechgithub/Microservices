package com.example.microservices.services.web;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Account DTO used to interact with {@link WebAccountService}
 * @author gopalpc
 *
 */

@JsonRootName("Account")
public class Account {


	protected Long id;
	protected String number;
	protected String owner;
	protected BigDecimal balance;

	protected Account() {
		balance = BigDecimal.ZERO;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String toString() {
		return "id:" + id + " number:" + number 
				     + " owner:" + owner 
				     + " balance:" + balance.toString();
	}

}
