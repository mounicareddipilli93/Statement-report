package com.assignment.dao;

import com.assignment.model.Account;

/** 
*This represents DAO interface
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
public interface AccountDAO {
	
	/**
	 * this method returns account object
     * @param accountNumber
     * @return
     */
	Account findByAccountNumber(String accountNumber);
}
