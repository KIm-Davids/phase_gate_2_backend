package com.semicolon.africa.validation;

import com.semicolon.africa.dtos.request.*;
import com.semicolon.africa.exceptions.MoneyValidationException;

public class MoneyAmountValidation {

    public static void validateExpenseAmount(ExpenseRequest request){
        if(request.getTotalExpenseAmount() < 0){
            throw new MoneyValidationException("Amount not valid");
        }
    }

    public static void validateUpdatedExpenses(UpdateExpenseRequest request){
        if(request.getNewTotalExpenseAmount() < 0){
            throw new MoneyValidationException("Amount not valid");
        }
    }

    public static void validateIncomeAmount(IncomeRequest request){
        if(request.getTotalIncomeAmount() < 0){
            throw new MoneyValidationException("Amount not valid");
        }
    }

    public static void validateUpdatedIncome(UpdateIncomeRequest request){
        if(request.getNewTotalIncomeAmount() < 0){
            throw new MoneyValidationException("Amount not valid");
        }
    }
}
