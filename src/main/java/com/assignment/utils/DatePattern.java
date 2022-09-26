package com.assignment.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The DatePattern provides methods to convert date patterns
 *
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
public class DatePattern {

    public static final Logger logger = LoggerFactory.getLogger(DatePattern.class);
    
    /**
    * Private Constructor restricting to create explicit object
    */
    private DatePattern() {
    	
    }
    /**
     * converts date pattern to dd.MM.yyyy
     *
     * @param date
     * @return
     */
    public static String convertDatePattern(String date) {
        try {
            Date dateToBeConverted = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(dateToBeConverted);
        } catch (Exception e) {
            logger.error("Error while converting date format to dd.MM.yyyy ", e);
        }

        return null;
    }
}
