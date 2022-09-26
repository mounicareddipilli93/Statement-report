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
public class StatementRowMapper implements RowMapper<Statement> {

    public static final Logger logger = LoggerFactory.getLogger(StatementRowMapper.class);
    
    /**
   	 *Method to convert row into an Object
   	 *
   	 * @param ResultSet result from SPringJDBC query.
   	 * @param rownum.
   	 * @return returns statement object.
   	*/
    @Override
    public Statement mapRow(ResultSet rs, int rowNum) {
        Statement statement = new Statement();

        try {
            statement.setId(rs.getLong("ID"));
            statement.setAccountId(rs.getLong("account_id"));
            statement.setDate(rs.getString("datefield"));
            statement.setAmount(rs.getString("amount"));
        } catch (Exception e) {
            logger.error("Error while converting the row into an statement object via row mapper", e);
        }

        return statement;
    }

}
