package com.assignment.service;

import com.assignment.model.Account;

/**
* Service interface
*
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
public interface AccountService {
	
	/**
	 * this method returns account object
     * @param accountNumber
     * @return
     */
	public Account findByAccountNumber(String accountNumber);
}
