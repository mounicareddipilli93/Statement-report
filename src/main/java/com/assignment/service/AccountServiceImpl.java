package com.assignment.service;

import com.assignment.dao.AccountDAO;
import com.assignment.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Service Implementation
*
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
@Service
public class AccountServiceImpl implements AccountService{
	
	private final AccountDAO accountDAO;

	@Autowired
	public AccountServiceImpl(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	/**
	 * this method returns account object
     * @param accountNumber
     * @return
     */
	@Override
	public Account findByAccountNumber(String accountNumber) {
		return accountDAO.findByAccountNumber(accountNumber);
	}
}
