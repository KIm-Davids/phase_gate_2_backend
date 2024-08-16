package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.dtos.response.DeleteAllResponse;
import com.semicolon.africa.dtos.response.ProfitResponse;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.models.Income;
import com.semicolon.africa.models.Profit;
import com.semicolon.africa.repository.ExpensesRepository;
import com.semicolon.africa.repository.IncomeRepository;
import com.semicolon.africa.repository.ProfitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.africa.validation.ProfitValidation.checkForNetLoss;
import static com.semicolon.africa.validation.ProfitValidation.validateProfitRequest;

@Service
public class ProfitService {

    private final ProfitRepository profitRepository;
    private final IncomeRepository incomeRepository;
    private final ExpensesRepository expensesRepository;

    private ProfitService(ProfitRepository profitRepository, IncomeRepository incomeRepository, ExpensesRepository expensesRepository){
        this.profitRepository = profitRepository;
        this.expensesRepository = expensesRepository;
        this.incomeRepository = incomeRepository;
    }

    public ProfitResponse calculateProfit(ProfitRequest request){
        Profit profit = new Profit();
        ProfitResponse response = new ProfitResponse();

        validateProfitRequest(request);
        Expenses expenses = setExpenseFromProfitRequest(request);
        Income income = setIncomeFromProfitRequest(request);

        double expenseAmount = expenses.getTotalExpenses();
        double incomeAmount = income.getTotalIncome();
        String advice = advice(income,expenses);
        double amount = incomeAmount - expenseAmount;
        String netAmount = checkForNetLoss(amount);


        profit.setNetProfit(netAmount);
        profitRepository.save(profit);
        response.setMessage(netAmount);
        response.setExpenses(expensesRepository.findAll());
        response.setIncome(incomeRepository.findAll());
        response.setAdvice(advice);
        return response;
    }

    public DeleteAllResponse deleteAllResponse(){
        DeleteAllResponse response= new DeleteAllResponse();
        profitRepository.deleteAll();
        return response;
    }

    private Expenses setExpenseFromProfitRequest(ProfitRequest request){
        Expenses expenses = new Expenses();
        expenses.setExpenseType(request.getExpenseType());
        expenses.setTotalExpenses(request.getTotalExpenseAmount());
        return expenses;
    }

    private Income setIncomeFromProfitRequest(ProfitRequest request){
        Income income = new Income();
        income.setIncomeType(request.getIncomeType());
        income.setTotalIncome(request.getTotalIncomeAmount());
        return income;
    }

    private String advice(Income income, Expenses expenses){
        String advice = "";
        if(expenses.getTotalExpenses() > income.getTotalIncome()){
            advice = "Please work on increasing your income !!!!";
        }
        return advice;
    }

//    private void getTotalExpenses(){
//        List<Expenses> totalExpenses = expensesRepository.findByAmount();
////        for(int counter = 0; counter < totalExpenses.size(); counter++){
////            if(totalExpenses.get(counter) > )
////        }
////        totalExpenses.
//    }
}
