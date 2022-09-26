package com.assignment.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DatePatternTest {

    @Test
    void shouldConvertFromInputDateFormatToSystemDateFormat() {
        assertEquals("01.06.2020", DatePattern.convertDatePattern("2020-06-01"));
        assertEquals("31.08.2020", DatePattern.convertDatePattern("2020-08-31"));
        assertNull(DatePattern.convertDatePattern(""));
        assertNull(DatePattern.convertDatePattern("InvalidDate"));
    }
}