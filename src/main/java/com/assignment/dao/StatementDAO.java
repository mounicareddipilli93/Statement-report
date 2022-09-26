package com.assignment.dao;

import java.util.List;

import com.assignment.model.Statement;

/**
* This represents DAO interface
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
public interface StatementDAO {
	
	/**
     * This findByFromDateAndToDate DAO method is used to get statement report.
     * when from date and to date are null then it will return default 3 months of statement
     *
     * @param accountId AccountId
     * @param fromDate  start date for the statement
     * @param toDate    end date for the statement
     * @return statement list
     */
	List<Statement> findByFromDateAndToDate(long accountId, String fromDate, String toDate);
	
	/**
     * This findByFromAmountAndToAmount DAO method is used to get statement report.
     * it returns statement by taking amount range
     *
     * @param accountId     AccountId
     * @param fromAmount    start amount for the statement
     * @param toAmount      end amount for the statement
     * @return statement list
     */
	List<Statement> findByFromAmountAndToAmount(long accountId, String fromAmount, String toAmount);
}
