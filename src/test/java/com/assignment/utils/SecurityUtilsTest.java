package com.assignment.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityUtilsTest {

    @Test
    void shouldMaskAccountNumber() {
        assertEquals(null,SecurityUtils.maskAccountNumber(null));
        assertEquals("",SecurityUtils.maskAccountNumber(""));
        assertEquals("1234",SecurityUtils.maskAccountNumber("1234"));
        assertEquals("*****6789",SecurityUtils.maskAccountNumber("123456789"));
    }
}
