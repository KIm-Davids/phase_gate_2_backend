package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.LogoutRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.LogoutResponse;
import com.semicolon.africa.dtos.response.RegisterUserResponse;
import com.semicolon.africa.models.User;

import java.util.List;

public interface UserServices {

    RegisterUserResponse registerUserWith(RegisterUserRequest request);
    List<User> getAllUsers();
    LoginResponse login(LoginRequest request);
    LogoutResponse logoutResponse(LogoutRequest request);

}
