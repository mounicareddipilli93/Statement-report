package com.assignment.model;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatementRowMapperTest {

    private StatementRowMapper mapper = new StatementRowMapper();

    @Test
    void shouldMapStatement() throws SQLException {
        // Given
        long statementId = 111L;
        long accountId = 123L;
        String date = "01.06.2020";
        String amount = "123.45";

        ResultSet rs = mock(ResultSet.class);

        when(rs.getLong("ID")).thenReturn(statementId);
        when(rs.getLong("account_id")).thenReturn(accountId);
        when(rs.getString("datefield")).thenReturn(date);
        when(rs.getString("amount")).thenReturn(amount);

        // When
        Statement statement = mapper.mapRow(rs, 1);

        // Then
        assertEquals(statementId, statement.getId());
        assertEquals(accountId, statement.getAccountId());
        assertEquals(date, statement.getDate());
        assertEquals(amount, statement.getAmount());
    }
}