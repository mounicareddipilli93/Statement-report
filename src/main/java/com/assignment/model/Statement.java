package com.assignment.model;

/**
* Represents a statement model class
* 
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
public class Statement {

    private long id;
    private long accountId;
    private String date;
    private String amount;
    
    /** 
    * Creates a statement with the specified name
    */
	public Statement() { }
	
	/** 
	 * Creates a statement with the specified name.
	 * 
	 * @param id The statement id.
	 * @param accountId The account id.
	 * @param date The date.
	 * @param amount amount.
	*/
	public Statement(long id, long accountId, String date, String amount) {
		this.id = id;
		this.accountId = accountId;
		this.date = date;
		this.amount = amount;
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
	 * setter method takes input ID.
	 * 
	 * @param long id 
	*/
	public void setId(long id) {
		this.id = id;
	}
	
	/** 
	 * getter method returns account id.
	 * 
	 * @return long accountId 
	*/
	public long getAccountId() {
		return accountId;
	}
	
	/** 
	 * setter method takes input account id.
	 * 
	 * @param long accountId 
	*/
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	/** 
	 * getter method returns date.
	 * 
	 * @return String date 
	*/
	public String getDate() {
		return date;
	}
	
	/** 
	 * setter method takes input date.
	 * 
	 * @param String date 
	*/
	public void setDate(String date) {
		this.date = date;
	}
	
	/** 
	 * getter method returns amount.
	 * 
	 * @return String amount 
	*/
	public String getAmount() {
		return amount;
	}
	/** 
	 * setter method takes input amount.
	 * 
	 * @param String amount 
	*/
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/** to string method to print object with values */
	@Override
	public String toString() {
		return "Statement [id=" + id + ", accountId=" + accountId + ", date=" + date + ", amount=" + amount + "]";
	}
}
