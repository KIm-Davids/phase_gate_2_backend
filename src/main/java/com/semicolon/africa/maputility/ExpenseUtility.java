package com.semicolon.africa.maputility;

import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.models.Expenses;

import java.time.LocalDateTime;

import static com.semicolon.africa.validation.MoneyAmountValidation.validateExpenseAmount;

public class ExpenseUtility {

    public static Expenses addRequestToExpense(ExpenseRequest request, Expenses expenses){
        validateExpenseAmount(request);
        expenses.setExpenseType(request.getExpenseType());
        expenses.setTotalExpenses(request.getTotalExpenseAmount());
        expenses.setLocalDateTime(LocalDateTime.now());
        expenses.setDescription(request.getDescription());
        return expenses;
    }
}
