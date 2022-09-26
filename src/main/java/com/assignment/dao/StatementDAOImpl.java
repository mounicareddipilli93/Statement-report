package com.assignment.dao;

import com.assignment.model.Statement;
import com.assignment.model.StatementRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.assignment.utils.DatePattern.convertDatePattern;

/**
 * This represents DAO Implementation
 *
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
@Transactional
@Repository
public class StatementDAOImpl implements StatementDAO {

    public static final Logger logger = LoggerFactory.getLogger(StatementDAOImpl.class);

    private static final int DEFAULT_STATEMENT_MONTHS = -3;

    private static final String SELECT_BY_DATES = "select * from statement where account_id=? and (datefield>? and datefield<?)";
    private static final String SELECT_BY_AMOUNTS = "select * from statement where account_id=? and (amount>? and amount<?)";

    private final JdbcTemplate template;

    @Autowired
    public StatementDAOImpl(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * This findByFromDateAndToDate DAO method is used to get statement report.
     * when from date and to date are null then it will return default 3 months of statement
     *
     * @param accountId AccountId
     * @param fromDate  start date for the statement
     * @param toDate    end date for the statement
     * @return statement list
     */
    @Override
    public List<Statement> findByFromDateAndToDate(long accountId, String fromDate, String toDate) {

        List<Statement> statementList = null;
        fromDate = convertDatePattern(fromDate);
        toDate = convertDatePattern(toDate);
        try {
            if (fromDate == null || toDate == null) {
                
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				Calendar cal = Calendar.getInstance();
				Date date = cal.getTime();
				toDate = dateFormat.format(date); //today's date
				cal.add(Calendar.MONTH, DEFAULT_STATEMENT_MONTHS);
				date = cal.getTime();
				fromDate = dateFormat.format(date); //3 months back date
            }

            logger.info("Querying statement for accountId: {} for dates: {} and {}", accountId, fromDate, toDate);
			statementList = template.query(SELECT_BY_DATES, new StatementRowMapper(), accountId, fromDate, toDate);
            logger.info("Found {} records", statementList.size());
        } catch (Exception e) {
            logger.error("Error while querying statement", e);
        }

        return statementList;
    }

    /**
     * This findByFromAmountAndToAmount DAO method is used to get statement report.
     * it returns statement by taking amount range
     *
     * @param accountId     AccountId
     * @param fromAmount    start amount for the statement
     * @param toAmount      end amount for the statement
     * @return statement list
     */
    @Override
    public List<Statement> findByFromAmountAndToAmount(long accountId, String fromAmount, String toAmount) {

		List<Statement> statementList = null;

        try {
			logger.info("Querying statement for accountId: {} for amount range: {} and {}", accountId, fromAmount, toAmount);
			statementList = template.query(SELECT_BY_AMOUNTS, new StatementRowMapper(), accountId, fromAmount, toAmount);
            logger.info("Found {} records", statementList.size());
            return statementList;
        } catch (Exception e) {
			logger.error("Error while querying statement", e);
        }
        return statementList;
    }
}
