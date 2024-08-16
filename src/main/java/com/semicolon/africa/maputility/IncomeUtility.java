package com.semicolon.africa.maputility;

import com.semicolon.africa.dtos.request.IncomeRequest;
import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.models.Income;

import java.time.LocalDateTime;

import static com.semicolon.africa.validation.MoneyAmountValidation.validateIncomeAmount;

public class IncomeUtility {

    public static Income addRequestToIncome(IncomeRequest request, Income income){
        validateIncomeAmount(request);
        income.setIncomeType(request.getIncomeType());
        income.setTotalIncome(request.getTotalIncomeAmount());
        income.setLocalDateTime(LocalDateTime.now());
        income.setDescription(request.getDescription());
        return income;
    }
}
