package com.assignment.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;


/**
 * This class has method converts the row into an object via row mapper
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
public class AccountRowMapper implements RowMapper<Account> {

    public static final Logger logger = LoggerFactory.getLogger(AccountRowMapper.class);
    
    /**
   	 *Method to convert row into an Object
   	 *
   	 * @param ResultSet result from SPringJDBC query.
   	 * @param rownum.
   	 * @return returns account object.
   	*/
    @Override
    public Account mapRow(ResultSet rs, int rowNum) {
        Account account = new Account();

        try {
            account.setId(rs.getLong("ID"));
            account.setAccountType(rs.getString("account_type"));
            account.setAccountNumber(rs.getString("account_number"));
        } catch (Exception e) {
            logger.error("Error while converting the row into an account object via row mapper", e);
        }

        return account;
    }

}
