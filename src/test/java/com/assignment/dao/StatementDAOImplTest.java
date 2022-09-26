package com.assignment.dao;

import com.assignment.model.Statement;
import com.assignment.model.StatementRowMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementDAOImplTest {

    @Mock private JdbcTemplate jdbcTemplate;
    @InjectMocks private StatementDAOImpl dao;

    private static final String SELECT_BY_DATES = "select * from statement where account_id=? and (datefield>? and datefield<?)";
    private static final String SELECT_BY_AMOUNTS = "select * from statement where account_id=? and (amount>? and amount<?)";

    @Test
    void shouldGetStatementByAccountIdAndDateRange() {
        // Given
        long accountId = 123L;

        List<Statement> statementList = asList(
                new Statement(1L, accountId, "10.06.2020", "100.00"),
                new Statement(2L, accountId, "20.07.2020", "200.00"),
                new Statement(3L, accountId, "11.08.2020", "300.00")
        );

        when(jdbcTemplate.query(eq(SELECT_BY_DATES), any(StatementRowMapper.class), eq(accountId), eq("01.06.2020"), eq("31.08.2020")))
                .thenReturn(statementList);

        // When
        List<Statement> actualStatementList = dao.findByFromDateAndToDate(accountId, "2020-06-01", "2020-08-31");

        // Then
        assertSame(statementList, actualStatementList);
    }

    @Test
    void shouldGetStatementForLast3MonthsWhenDateRangeIsNotSpecified() {
        // Given
        long accountId = 123L;

        List<Statement> statementList = emptyList();
        when(jdbcTemplate.query(eq(SELECT_BY_DATES), any(StatementRowMapper.class), eq(accountId), anyString(), anyString())).thenReturn(statementList);

        // When
        List<Statement> actualStatementList = dao.findByFromDateAndToDate(accountId, null, null);

        // Then
        assertSame(statementList, actualStatementList);

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String expectedToDate = dateFormat.format(calendar.getTime()); // today's date
        calendar.add(Calendar.MONTH, -3);
        String expectedFromDate = dateFormat.format(calendar.getTime()); // 3 months back date

        verify(jdbcTemplate).query(eq(SELECT_BY_DATES), any(StatementRowMapper.class), eq(accountId), eq(expectedFromDate), eq(expectedToDate));
    }

    @Test
    void shouldReturnNullWhenStatementByAccountIdAndDateRangeQueryThrowsException() {
        // Given

        when(jdbcTemplate.query(eq(SELECT_BY_DATES), any(StatementRowMapper.class), any(), any(), any()))
                .thenThrow(new RuntimeException("Some Error"));

        // When
        List<Statement> actualStatementList = dao.findByFromDateAndToDate(123L, "2020-06-01", "2020-08-31");

        // Then
        assertNull(actualStatementList);
    }

    @Test
    void shouldGetStatementByAccountIdAndAmountRange() {
        // Given
        long accountId = 123L;
        String fromAmount = "100.00";
        String toAmount = "500.00";

        List<Statement> statementList = asList(
                new Statement(1L, accountId, "10.06.2020", "150.00"),
                new Statement(2L, accountId, "20.07.2020", "200.00"),
                new Statement(3L, accountId, "11.08.2020", "300.00")
        );

        when(jdbcTemplate.query(eq(SELECT_BY_AMOUNTS), any(StatementRowMapper.class), eq(accountId), eq(fromAmount), eq(toAmount)))
                .thenReturn(statementList);

        // When
        List<Statement> actualStatementList = dao.findByFromAmountAndToAmount(accountId, fromAmount, toAmount);

        // Then
        assertSame(statementList, actualStatementList);
    }

    @Test
    void shouldReturnNullWhenStatementByAccountIdAndAmountRangeQueryThrowsException() {
        // Given
        when(jdbcTemplate.query(eq(SELECT_BY_AMOUNTS), any(StatementRowMapper.class), any(), any(), any()))
                .thenThrow(new RuntimeException("Some Error"));

        // When
        List<Statement> actualStatementList = dao.findByFromAmountAndToAmount(123L, "100.00", "500.00");

        // Then
        assertNull(actualStatementList);
    }
}
