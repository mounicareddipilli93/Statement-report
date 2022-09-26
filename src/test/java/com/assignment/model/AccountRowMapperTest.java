package com.assignment.model;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountRowMapperTest {

    private AccountRowMapper mapper = new AccountRowMapper();

    @Test
    void shouldMapAccount() throws SQLException {
        // Given
        long accountId = 123L;
        String accountType = "Current";
        String accountNumber = "1234567891234";

        ResultSet rs = mock(ResultSet.class);

        when(rs.getLong("ID")).thenReturn(accountId);
        when(rs.getString("account_type")).thenReturn(accountType);
        when(rs.getString("account_number")).thenReturn(accountNumber);

        // When
        Account account = mapper.mapRow(rs, 1);

        // Then
        assertEquals(accountId, account.getId());
        assertEquals(accountType, account.getAccountType());
        assertEquals(accountNumber, account.getAccountNumber());
    }
}