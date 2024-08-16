package com.semicolon.africa.services;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.UpdateExpenseRequest;
import com.semicolon.africa.dtos.response.DeleteExpenseResponse;
import com.semicolon.africa.dtos.response.ExpenseResponse;
import com.semicolon.africa.dtos.response.UpdateExpenseResponse;
import com.semicolon.africa.models.Expenses;

import java.util.List;

public interface ExpenseServiceInterface {

    ExpenseResponse addExpensesToRepository(ExpenseRequest request);
    List<Expenses> getAllExpense();
    DeleteExpenseResponse deleteExpensesByExpenseType(ExpenseType expenseType);
    UpdateExpenseResponse updateExpensesByExpenseType(UpdateExpenseRequest request);
}
