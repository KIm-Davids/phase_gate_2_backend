package com.semicolon.africa.maputility;

import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.IncomeRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.RegisterUserResponse;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.models.Income;
import com.semicolon.africa.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserUtility {

    public static void addRequestToUser(RegisterUserRequest request, User user){
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

    }

    public static RegisterUserResponse createRegisterSuccessfulResponse(User user){
        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(user.getEmail());
        response.setMessage(user.getEmail() + " registered successfully");
        return response;
    }

    public static void mapUserToExpenses(ExpenseRequest request){
        User user = new User();
        user.setExpenseType(request.getExpenseType());
        ExpenseRequest expenses = new ExpenseRequest();
        expenses.setTotalExpenseAmount(request.getTotalExpenseAmount());
        user.setExpenses((List<Expenses>) expenses);
    }


    public static void mapUserToIncome(IncomeRequest request){
        User user = new User();
        user.setIncomeType(request.getIncomeType());
        IncomeRequest income = new IncomeRequest();
        income.setTotalIncomeAmount(request.getTotalIncomeAmount());
        user.setIncome((List<Income>) income);
    }

}


