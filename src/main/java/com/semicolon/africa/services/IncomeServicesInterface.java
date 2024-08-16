package com.semicolon.africa.services;

import com.semicolon.africa.constants.IncomeType;
import com.semicolon.africa.dtos.request.IncomeRequest;
import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.dtos.request.UpdateIncomeRequest;
import com.semicolon.africa.dtos.response.*;
import com.semicolon.africa.models.Income;

import java.util.List;

public interface IncomeServicesInterface {

    IncomeResponse addIncomeToRepository(IncomeRequest request);
    List<Income> getAllIncome();
    DeleteIncomeResponse deleteIncomeByIncomeType(IncomeType incomeType);
    UpdateIncomeResponse updateIncomeById(UpdateIncomeRequest request);

}
