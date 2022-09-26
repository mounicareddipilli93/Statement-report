package com.assignment.model;

/** Represents an account model class
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
public class Account {

    private long id;
    private String accountType;
    private String accountNumber;
    
    /** 
    * Creates an account with the specified name
    */
	public Account() { }
	
	/** Creates an account with the specified name.
	 * @param id The account id.
	 * @param accountType The account type.
	 * @param accountNumber The account number.
	*/
	public Account(long id, String accountType, String accountNumber) {
		this.id = id;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
	}
	
	/** 
	 * getter method returns ID.
	 * 
	 * @return long id 
	*/
	public long getId() {
		return id;
	}
	
	/** 
	 * setter method takes input id.
	 * 
	 * @param long accountType 
	*/
	public void setId(long id) {
		this.id = id;
	}
	
	/** 
	 * getter method returns ID.
	 * 
	 * @return long id 
	*/
	public String getAccountType() {
		return accountType;
	}
	
	/** 
	 * setter method takes input accountType.
	 * 
	 * @param String accountType 
	*/
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	/** 
	 * getter method returns accountNumber.
	 * 
	 * @return String accountNumber 
	*/
	public String getAccountNumber() {
		return accountNumber;
	}
	
	/** 
	 * setter method takes input accountNumber.
	 * 
	 * @param String id 
	*/
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
