package com.semicolon.africa.services;
import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.constants.IncomeType;
import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.IncomeRequest;
import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.dtos.response.IncomeResponse;
import com.semicolon.africa.dtos.response.ProfitResponse;
import com.semicolon.africa.models.Income;
import com.semicolon.africa.models.Profit;
import com.semicolon.africa.repository.IncomeRepository;
import com.semicolon.africa.repository.ProfitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ProfitServicesTest {

    @Autowired
    private ProfitRepository repository;
    @Autowired
    private ProfitService service;
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private IncomeServices services;


    @Test
    void calculateProfit() {
        ProfitRequest request = generateProfit();
        ProfitResponse amount = service.calculateProfit(request);
        assertThat(amount.getMessage()).contains("successfully");

    }

    @Test
    void testThatTheValuesSavedInTheRepoCanBeRetrieved(){
        IncomeRequest income = new IncomeRequest();
        income.setIncomeType(IncomeType.ASSETS);
        income.setTotalIncomeAmount(1000);
        services.addIncomeToRepository(income);
        List<Income> income1 = incomeRepository.findByIncomeType(income.getIncomeType());
        System.out.println(income1.getFirst().getIncomeType());
        assertThat(income1).isNotNull();
    }




    private ProfitRequest generateProfit(){
        IncomeRequest incomeRequest = new IncomeRequest();
        ExpenseRequest expenseRequest  = new ExpenseRequest();
        ProfitRequest request = new ProfitRequest();
        request.setIncomeType(IncomeType.ASSETS);
        request.setTotalIncomeAmount(1000);

        request.setExpenseType(ExpenseType.FEEDING);
        request.setTotalExpenseAmount(500);

        return request;
    }
}