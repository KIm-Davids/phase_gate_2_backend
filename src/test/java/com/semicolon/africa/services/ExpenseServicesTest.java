package com.semicolon.africa.services;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.dtos.request.DeleteExpenseRequest;
import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.UpdateExpenseRequest;
import com.semicolon.africa.dtos.response.DeleteExpenseResponse;
import com.semicolon.africa.dtos.response.UpdateExpenseResponse;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.repository.ExpensesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseServicesTest {

    @Autowired
    private ExpensesRepository repository;

    @Autowired
    private ExpenseServices services;

    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }

    @Test
    void addExpensesToRepository() {
        ExpenseRequest expenses = generateExpenses();
        services.addExpensesToRepository(expenses);
        assertThat(repository.findAll().size()).isEqualTo(1);
    }

    @Test
    void deleteExpensesByMonth() {
        ExpenseRequest expenses = generateExpenses();
        services.addExpensesToRepository(expenses);
        DeleteExpenseResponse response = services.deleteExpensesByExpenseType("TAX");
        assertThat(response.getMessage()).contains("deleted");
    }

    @Test
    void updateExpensesByMonth() {
        UpdateExpenseRequest request = new UpdateExpenseRequest();
        UpdateExpenseResponse response = services.updateExpensesByExpenseType(request);
        assertThat(response.getMessage()).contains("Updated");
    }


    private ExpenseRequest generateExpenses(){
        Expenses expenses = new Expenses();
        ExpenseRequest request = new ExpenseRequest();
        request.setId("66b5d4d25b4c23595cb2cd46");
        request.setTotalExpenseAmount(1000);
        request.setExpenseType(ExpenseType.TAX);

        return request;
    }

    private UpdateExpenseRequest generateNewExpenses(){
        Expenses expenses = new Expenses();
        UpdateExpenseRequest request = new UpdateExpenseRequest();
        request.setNewId("66b5d86c07ef470e0f29e287");
        request.setNewTotalExpenseAmount(2000);
        request.setNewExpenseType(ExpenseType.FEEDING);
        request.setNewDescription("Money well spent");
        request.setNewLocalDateTime(LocalDateTime.now());

        return request;
    }
}