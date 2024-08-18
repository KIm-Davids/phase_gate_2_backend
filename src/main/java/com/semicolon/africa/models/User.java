package com.semicolon.africa.models;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.constants.IncomeType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class User {

    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private boolean isLoggedIn;
    private IncomeType incomeType;
    private ExpenseType expenseType;
    private List<Income> income;
    private List<Expenses> expenses;
}
