package com.assignment.utils;

import org.apache.commons.lang.StringUtils;

/**
 * The SecurityUtils is provides utility methods for security related purposes. Ex. Masking sensitive info
 *
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
public class SecurityUtils {
	
	 /**
	 * Private Constructor restricting to create explicit object
	 */
	private SecurityUtils() {
		
	}
    /**
     * Mask accountNumber with *'s allowing only the last 4 digits to be visible
     *
     * @param accountNumber
     * @return
     */
    public static String maskAccountNumber(String accountNumber) {
        return StringUtils.isEmpty(accountNumber)
				? accountNumber
				: accountNumber.replaceAll(".(?=.{4})", "*");
    }
}
