package com.semicolon.africa.validation;

import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.exceptions.MoneyValidationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfitValidation {

    public static void validateProfitRequest(ProfitRequest request) {
        if (request.getTotalIncomeAmount() < 0 || request.getTotalExpenseAmount() < 0) {
            throw new MoneyValidationException("Invalid Amount");
        }
    }

    public static String checkForNetLoss(double amount){
        if(amount < 0){
            return "Your net loss is: " + amount;
        }
        else{
            return "Your net profit is: " + amount;
        }
    }
}
