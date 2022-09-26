package com.assignment.controller;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    private HomeController controller = new HomeController();

    @Test
    void testShouldReturnCorrectViews() {
        assertEquals("/login", controller.login());
        assertEquals("login", controller.root());
        assertEquals("index", controller.index());
        assertEquals("get_statement", controller.getStatement());
        assertEquals("get_statement_daterange", controller.getStatementDaterange());
        assertEquals("get_statement_amountrange", controller.getStatementAmountrange());
    }
}