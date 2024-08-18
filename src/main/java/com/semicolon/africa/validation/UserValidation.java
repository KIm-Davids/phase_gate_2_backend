package com.semicolon.africa.validation;

import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.exceptions.InvalidDetailsException;

public class UserValidation {

    public static void validateInput(LoginRequest request){
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
        if(request.getEmail().contains(" ")){
            throw new InvalidDetailsException("Input Email again !");
        }
    }
}
