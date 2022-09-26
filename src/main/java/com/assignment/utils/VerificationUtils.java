package com.assignment.utils;

import java.security.InvalidParameterException;

import org.apache.commons.lang.StringUtils;

/**
 * The VerificationUtils validates values as required and throws exceptions when invalid
 *
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
public class VerificationUtils {
	
	/**
	* Private Constructor restricting to create explicit object
	*/
	private VerificationUtils() {
		
	}
	
	/**
     * Verifies String value blank and if it blanks throws InvalidParameterException
     * with a custom message
     *
     * @param value
     * @param errorMessage
     */
    public static void verifyNotBlank(String value, String errorMessage) {
        if (StringUtils.isBlank(value)) {
            throw new InvalidParameterException(errorMessage);
        }
    }
    
    /**
     * Verifies String value and if it is invalid size throws InvalidParameterException
     * with a custom message
     *
     * @param value
     * @param size
     * @param errorMessage
     */
    public static void verifyLength(String value,int size,String errorMessage) {
        if (StringUtils.length(value) != size) {
            throw new InvalidParameterException(errorMessage);
        }
    }
}
