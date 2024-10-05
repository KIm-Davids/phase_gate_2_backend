package com.semicolon.africa.validation;

import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.IncomeRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.exceptions.InvalidDetailsException;
import com.semicolon.africa.models.User;

public class UserValidation {

    public static void validateInput(LoginRequest request){
        if(!request.getEmail().contains("mail.com")){
            throw new InvalidDetailsException("Invalid email");
        }
    }

    public static void validateEmail(User request){
        if(!request.getEmail().contains("mail.com")){
            throw new InvalidDetailsException("Invalid email");
        }
    }

    public static void validateInputForNullEntry(LoginRequest request){
        if(request.getEmail().contains(" ")){
            throw new InvalidDetailsException("Input Email again !");
        }
    }

    public static void validateInput(RegisterUserRequest request){
        if(!request.getEmail().contains("mail.com")){
            throw new InvalidDetailsException("Invalid email");
        }
    }

    public static void validateInputForNullEntry(RegisterUserRequest request){
        if(request.getEmail().contains(" ") || request.getFirstName().contains(" ") || request.getLastName().contains(" ")){
            throw new InvalidDetailsException("Invalid Entry pls try again !");
        }
    }
}
