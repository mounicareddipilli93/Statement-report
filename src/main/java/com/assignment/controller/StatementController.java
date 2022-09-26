package com.assignment.controller;

import static com.assignment.utils.SecurityUtils.maskAccountNumber;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.model.Account;
import com.assignment.model.Statement;
import com.assignment.service.AccountService;
import com.assignment.service.StatementService;
import com.assignment.utils.VerificationUtils;

/**
 * The StatementController to provide the Services to statementReport returns statement list
 * 
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
@Controller
@RequestMapping("/statement")
public class StatementController {

    public static final Logger logger = LoggerFactory.getLogger(StatementController.class);
    
    private static final int ACCOUNT_NUMBER_LENGTH = 13;

    private final AccountService accountService;
    private final StatementService statementService;

    @Autowired
    public StatementController(AccountService accountService, StatementService statementService) {
        this.accountService = accountService;
        this.statementService = statementService;
    }

    /**
     * This method is used to get statement report.
     *
     * @param accountNumber accountNumber for which the statement is requested
     * @param fromDate      starting date of the statement
     * @param toDate        end date of the statement
     * @return statement
     */
    @GetMapping("/statement-report")
    public ModelAndView getStatement(
            @RequestParam(name = "accountNumber") String accountNumber,
            @RequestParam(name = "fromDate", required = false) String fromDate,
            @RequestParam(name = "toDate", required = false) String toDate) {

		try {
			String maskedAccountNumber = maskAccountNumber(accountNumber);
            logger.info("Statement requested by user: {} for account: {} from date: {} to date: {}", getUsername(), maskedAccountNumber, fromDate, toDate);

            VerificationUtils.verifyNotBlank(accountNumber, "accountNumber cannot be blank");
            VerificationUtils.verifyLength(accountNumber,ACCOUNT_NUMBER_LENGTH,"accountNumber length is invalid");
            
            Account account = accountService.findByAccountNumber(accountNumber);
			List<Statement> statementList = statementService.findByFromDateAndToDate(account.getId(), fromDate, toDate);

            return createStatementResponse(account, statementList);
        } catch (Exception e) {
            logger.error("Error while fetching statement", e);
            return createErrorResponse(e);
        }
    }

    /**
     * This method is used to get statement report.
     *
     * @param accountNumber accountNumber for which the statement is requested
     * @param fromAmount    opening amount for the statement
     * @param toAmount      closing amount for the statement
     * @return statement
     */
    @GetMapping("/statement-report-amountrange")
    public ModelAndView getStatementByAmountRange(
            @RequestParam(name = "accountNumber") String accountNumber,
            @RequestParam(name = "fromAmount") String fromAmount,
            @RequestParam(name = "toAmount") String toAmount) {

        try {
        	String maskedAccountNumber = maskAccountNumber(accountNumber);
            logger.info("Statement requested by user: {} for account: {} from amount: {} to amount: {}", getUsername(),maskedAccountNumber, fromAmount, toAmount);

            VerificationUtils.verifyNotBlank(accountNumber, "accountNumber cannot be blank");
            VerificationUtils.verifyLength(accountNumber,ACCOUNT_NUMBER_LENGTH,"accountNumber length is invalid");
            VerificationUtils.verifyNotBlank(fromAmount, "fromAmount cannot be blank");
            VerificationUtils.verifyNotBlank(toAmount, "toAmount cannot be blank");

            Account account = accountService.findByAccountNumber(accountNumber);
			List<Statement> statementList = statementService.findByFromAmountAndToAmount(account.getId(), fromAmount, toAmount);

            return createStatementResponse(account, statementList);
        } catch (Exception e) {
            logger.error("Error while fetching statement", e);
            return createErrorResponse(e);
        }
    }
    
    /**
     * This method is used to get logged in user name of the current session.
     *
     * @return username
     */
    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
    /**
     * This method is used to prepare model and view for successful statement report.
     *
     * @param account
     * @param statementList
     * @return model
     */
    private static ModelAndView createStatementResponse(Account account, List<Statement> statementList) {
        ModelAndView model = new ModelAndView();
        model.addObject("accountNumber", maskAccountNumber(account.getAccountNumber()));
        model.addObject("accountType", account.getAccountType());
        model.addObject("statementList", statementList.size()>0?statementList:null);
        model.setViewName("statement_list");
        return model;
    }
    
    /**
     * This method is used to prepare model and view for Error case.
     *
     * @param exception
     * @return model
     */
    private static ModelAndView createErrorResponse(Exception e) {
        ModelAndView model = new ModelAndView();
        model.addObject("message", e.getMessage());
        model.addObject("exception", e);
        model.setViewName("custom_error");
        return model;
    }
}
