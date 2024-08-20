package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.dtos.response.DeleteAllResponse;
import com.semicolon.africa.dtos.response.ProfitResponse;
import com.semicolon.africa.exceptions.InvalidDetailsException;
import com.semicolon.africa.exceptions.UserLoginException;
import com.semicolon.africa.exceptions.UserNotFoundException;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.models.Income;
import com.semicolon.africa.models.Profit;
import com.semicolon.africa.models.User;
import com.semicolon.africa.repository.ExpensesRepository;
import com.semicolon.africa.repository.IncomeRepository;
import com.semicolon.africa.repository.ProfitRepository;
import com.semicolon.africa.repository.UserRepsoitory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.semicolon.africa.validation.ProfitValidation.checkForNetLoss;
import static com.semicolon.africa.validation.ProfitValidation.validateProfitRequest;

@Service
public class ProfitService {

    private final ProfitRepository profitRepository;
    private final IncomeRepository incomeRepository;
    private final ExpensesRepository expensesRepository;
    private final UserRepsoitory userRepository;
//    private final Expenses expenses;

    private ProfitService(ProfitRepository profitRepository, IncomeRepository incomeRepository, ExpensesRepository expensesRepository, UserRepsoitory userRepository){
        this.profitRepository = profitRepository;
        this.expensesRepository = expensesRepository;
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    public ProfitResponse calculateProfit(ProfitRequest request){
        Profit profit = new Profit();
        User user = new User();
        ProfitResponse response = new ProfitResponse();
        validateProfitRequest(request);
//        checkIfUserIsLoggedIn(loginRequest);
        Expenses expenses = setExpenseFromProfitRequest(request);
        Income income = setIncomeFromProfitRequest(request);

        double expenseAmount = expenses.getTotalExpenses();
        double incomeAmount = income.getTotalIncome();
        String advice = advice(income,expenses);
        double amount = incomeAmount - expenseAmount;
        String netAmount = checkForNetLoss(amount);



        Expenses saveTotalExpense = calculateTotalExpenses(user);
        Income saveTotalIncome = calculateTotalIncome(user);

        double totalProfit = saveTotalIncome.getTotalIncome() - saveTotalExpense.getTotalExpenses();
        profit.setNetProfit(totalProfit);
        profitRepository.save(profit);
        expensesRepository.save(saveTotalExpense);
        incomeRepository.save(saveTotalIncome);

//        profit.setNetProfit(netAmount);
        profit.setLocalDateTime(LocalDateTime.now());
        profitRepository.save(profit);

        response.setEmail(user.getEmail());
        response.setNetAmount(netAmount);

        response.setIncome(incomeRepository.findAll());
        response.setExpenses(expensesRepository.findAll());
        response.setLocalDateTime(LocalDateTime.now());

        return response;
    }

    private Expenses calculateTotalExpenses(User user) {
        List<Expenses> expenseList = expensesRepository.findAll();
        Expenses saveTotalExpense = new Expenses();

        double expenseListAmount = 0;
        for (Expenses value : expenseList) {
            expenseListAmount += value.getTotalExpenses();
        }

        saveTotalExpense.setTotalExpenses(expenseListAmount);
        return saveTotalExpense;
    }

    private Income calculateTotalIncome(User user){
        List<Income> incomeList = incomeRepository.findAll();

        double incomeListAmount = 0;
        for(Income value : incomeList){
            incomeListAmount += value.getTotalIncome();
        }

        Income saveTotalExpense = new Income();
        saveTotalExpense.setTotalIncome(incomeListAmount);
        return saveTotalExpense;
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

    private void checkIfUserIsLoggedIn(LoginRequest loginRequest){
        if(userRepository.findUserByEmail(loginRequest.getEmail()).equals(loginRequest.getEmail())){
            User user = userRepository.findUserByEmail(loginRequest.getEmail());
            user.setLoggedIn(true);
        }
        User user = userRepository.findUserByEmail(loginRequest.getEmail());

        if(!user.isLoggedIn()){
            throw new UserLoginException("Please Ensure you are logged in !");
        }
    }

    private ProfitResponse findDataByUser(LoginRequest loginRequest){
        ProfitResponse profitResponse = new ProfitResponse();
       if(!userRepository.findUserByEmail(loginRequest.getEmail()).equals(loginRequest.getEmail())){
           throw new InvalidDetailsException("Please Login Again !");
       }

        if(userRepository.findUserByEmail(loginRequest.getEmail()).equals(loginRequest.getEmail())){
            User user = userRepository.findUserByEmail(loginRequest.getEmail());
            user.setLoggedIn(true);
        }


//        Profit profit = profitRepository.findDataByUser(loginRequest.getEmail());
        User response = createAListForUser(loginRequest);

        String email = response.getEmail();
        List<Expenses> expensesList = response.getExpenses();
        List<Income> incomeList= response.getIncome();

        profitResponse.setEmail(email);
        profitResponse.setIncome(incomeList);
        profitResponse.setExpenses(expensesList);

       return profitResponse;
    }

    private User createAListForUser(LoginRequest loginRequest){
        Expenses expense = new Expenses();
        Income income = new Income();

        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        user.setExpenseType(expense.getExpenseType());
        user.setIncomeType(income.getIncomeType());

        expensesRepository.save(expense);
        incomeRepository.save(income);

        double expenses = expensesRepository.findExpensesByExpenseType(user.getExpenseType()).getTotalExpenses();
        double incomeAmount = incomeRepository.findByIncomeType(user.getIncomeType()).getTotalIncome();

        expense.setTotalExpenses(expenses);
        income.setTotalIncome(incomeAmount);
//
//        user.getExpenses().add(expense);
//        user.getIncome().add(income);

        return user;
    }

    private void validateIfUserExists(LoginRequest request){
        boolean userExists = userRepository.existsByEmail(request.getEmail());
        if(!userExists){
            throw new UserNotFoundException("User does not exists");
        }
    }

    public ProfitResponse checkIfUserIsOnline(LoginRequest request){
        validateIfUserExists(request);
        User user = userRepository.findUserByEmail(request.getEmail());
        boolean userOnline = user.isLoggedIn();

        checkIfUserIsLoggedIn(request);

        if (!userOnline) {
            throw new UserLoginException("Please Login !");
        }
        createAListForUser(request);
        return findDataByUser(request);
    }

}
