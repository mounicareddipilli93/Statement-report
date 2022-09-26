package com.assignment.utils;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class VerificationUtilsTest {

    @Test
    void shouldNotFailWhenValuesIsNotBlank() {
        try {
            VerificationUtils.verifyNotBlank("123", "Value cannot be blank");
            // No exception means all good!!
        } catch (Exception e) {
            fail("Exception not expected", e);
        }
    }

    @Test
    void shouldFailWhenValueIsNull() {
        // Given
        String message = "Value cannot be blank";

        try {
            // When
            VerificationUtils.verifyNotBlank(null, message);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Then
            assertEquals(InvalidParameterException.class, e.getClass());
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    void shouldFailWhenValueIsEmpty() {
        // Given
        String message = "Value cannot be blank";

        try {
            // When
            VerificationUtils.verifyNotBlank("", message);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Then
            assertEquals(InvalidParameterException.class, e.getClass());
            assertEquals(message, e.getMessage());
        }
    }
    
    @Test
    void shouldNotFailWhenValueLengthIsValid() {
        try {
            VerificationUtils.verifyLength("0012250016001",13,"accountNumber length is invalid");
            // No exception means all good!!
        } catch (Exception e) {
            fail("Exception not expected", e);
        }
    }
    
    @Test
    void FailWhenValueLengthIsNotValid() {
    	String message = "accountNumber length is invalid";
        try {
            VerificationUtils.verifyLength("123",13,message);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
        	assertEquals(InvalidParameterException.class, e.getClass());
            assertEquals(message, e.getMessage());
        }
    }
}