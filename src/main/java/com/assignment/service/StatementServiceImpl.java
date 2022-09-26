package com.assignment.service;

import com.assignment.dao.StatementDAO;
import com.assignment.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Service interface implementation
*
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
@Service
public class StatementServiceImpl implements StatementService{

	private final StatementDAO statementDAO;

	@Autowired
	public StatementServiceImpl(StatementDAO statementDAO) {
		this.statementDAO = statementDAO;
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
		return statementDAO.findByFromDateAndToDate(accountId, fromDate, toDate);
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
		return statementDAO.findByFromAmountAndToAmount(accountId, fromAmount, toAmount);
	}
}
