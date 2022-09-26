package com.assignment.dao;

import static com.assignment.utils.SecurityUtils.maskAccountNumber;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.model.Account;

/**
* This represents DAO Implementation
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
@Transactional
@Repository
public class AccountDAOImpl implements AccountDAO{

	public static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

	private static final String SQL = "select * from account where account_number=?";

	private final JdbcTemplate template;

	@Autowired
	public AccountDAOImpl(JdbcTemplate template) {
		this.template = template;
	}
	
	/**
	 * this method returns account object
     * @param accountNumber
     * @return
     */
	@Override
	public Account findByAccountNumber(String accountNumber) {
		try{
			RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
			return template.queryForObject(SQL, rowMapper, accountNumber);
		}catch(EmptyResultDataAccessException e){
			logger.info("Account number not found: {}",maskAccountNumber(accountNumber));
			throw new NoSuchElementException("Account Number not found");
		}catch(Exception e) {
			throw e;
		}
	}
}
