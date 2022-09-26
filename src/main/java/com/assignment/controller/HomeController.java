package com.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
* The HomeController class provides the Services to redirects the html pages 
*
* @author  Rajesh Majji
* @version 1.0
* @since   31/08/2020 
*/
@Controller
public class HomeController {
	
	@GetMapping("/login")
    public String login() {
        return "/login";
    }
	
	@GetMapping("/")
    public String root() {
        return "login";
    }

	@GetMapping("/index")
    public String index() {
        return "index";
    }

	@GetMapping("/get-statement")
    public String getStatement(){
        return "get_statement";
    }

	@GetMapping("/get-statement-daterange")
    public String getStatementDaterange(){
        return "get_statement_daterange";
    }

	@GetMapping("/get-statement-amountrange")
    public String getStatementAmountrange(){
        return "get_statement_amountrange";
    }
}
