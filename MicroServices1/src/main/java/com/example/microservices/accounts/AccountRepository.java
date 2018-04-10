package com.example.microservices.accounts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/*
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long>{
*/

@Component
public class AccountRepository {
	
	/** 
	 * find the account number which matches specified string 
	 *  
	 * @param  accountNumber
	 * @return Account if found, otherwise null
	 */
	public Account findByNumber(String accountNumber) {
	
		Account acc = new Account();
		
		Long id = new Long(1);
		acc.setId(id);
		acc.setNumber(accountNumber);
		acc.setOwner("gopal");
		acc.setBalance(new BigDecimal(100000));
		
		return acc;
	}
	
	/** 
	 * find owner name which matches the specified string
	 * 
	 * @param owner
	 * @return List<account> if found, otherwise null
	 */
	public List<Account> findyByOwnerContainingIgnoreCase(String owner){
		List<Account> accList = new ArrayList<Account>();
		
		Account acc = new Account();
		
		acc.setId(Long.valueOf("1"));
		acc.setNumber("123456789");
		acc.setOwner("gopal");
		acc.setBalance(new BigDecimal(100000));
	
		accList.add(acc);

		acc=new Account();

		acc.setId(Long.valueOf("2"));
		acc.setNumber("223456789");
		acc.setOwner("gopal2");
		acc.setBalance(new BigDecimal(200000));

		accList.add(acc);

		return accList;
	}
	
	/** 
	 *  Fetch the numbers of accounts
	 * 
	 * @return the number of accounts
	 */
	
	//@Query("SELECT count(*) from Account")
	public int countAccounts() {
		
		return 1;
	}
}

