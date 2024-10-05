package com.semicolon.africa.services;

import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.LoginRequest;
import com.semicolon.africa.dtos.request.LogoutRequest;
import com.semicolon.africa.dtos.request.RegisterUserRequest;
import com.semicolon.africa.dtos.response.LoginResponse;
import com.semicolon.africa.dtos.response.LogoutResponse;
import com.semicolon.africa.dtos.response.RegisterUserResponse;
import com.semicolon.africa.exceptions.EmailExistsException;
import com.semicolon.africa.exceptions.IncorrectPasswordException;
import com.semicolon.africa.exceptions.InvalidDetailsException;
import com.semicolon.africa.exceptions.UserNotFoundException;
import com.semicolon.africa.models.User;
import com.semicolon.africa.repository.UserRepsoitory;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.africa.maputility.UserUtility.*;
import static com.semicolon.africa.validation.UserValidation.validateInput;
import static com.semicolon.africa.validation.UserValidation.validateInputForNullEntry;

@Service
@AllArgsConstructor
public class UserServicesImpl implements UserServices{

    private UserRepsoitory userRepository;
    private ExpenseServices expenseServices;

    @Override
    public RegisterUserResponse registerUserWith(RegisterUserRequest request) {
        validateExistingEmail(request.getEmail());
        validateInput(request);
        validateInputForNullEntry(request);
        User user = new User();
        addRequestToUser(request, user);
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return createRegisterSuccessfulResponse(user);
    }


    private void validateExistingEmail(String email) {
        boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail)throw new EmailExistsException(email+" already exists");
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public LogoutResponse logoutResponse(LogoutRequest request) {
        User user = findByEmail(request.getEmail());
        user.setLoggedIn(false);
        userRepository.save(user);
        LogoutResponse response = new LogoutResponse();
        response.setMessage("user logged out successfully");
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        User user = findByEmail(request.getEmail());
        LoginResponse response = new LoginResponse();
        validatePassword(request);
        validateInput(request);
        validateInputForNullEntry(request);
        user.setLoggedIn(true);
        userRepository.save(user);
//        userRepository.
        response.setMessage("User Logged In successfully");
        return loginResponse(user);
    }

    private void validatePassword(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UserNotFoundException("email not found"));
        if (!(user.getPassword().equals(request.getPassword())))
            throw new IncorrectPasswordException("invalid details");
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("user not found"));
    }


    private LoginResponse loginResponse(User user){
        LoginResponse response = new LoginResponse();
        response.setEmail(user.getEmail());
        response.setMessage(user.getEmail() + " logged in successfully");
        response.setLoggedIn(true);
        return response;
    }

}
