package com.semicolon.africa.web.controllers;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.UpdateExpenseRequest;
import com.semicolon.africa.dtos.response.ApiResponse;
import com.semicolon.africa.dtos.response.DeleteExpenseResponse;
import com.semicolon.africa.dtos.response.ExpenseResponse;
import com.semicolon.africa.dtos.response.UpdateExpenseResponse;
import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.services.ExpenseServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/expenses")
@AllArgsConstructor
public class ExpenseController {
    private ExpenseServices services;

    @PostMapping("/add-expense")
    public ResponseEntity<?> addExpenses(@RequestBody ExpenseRequest request){
        try{
            ExpenseResponse response = services.addExpensesToRepository(request);
            return new ResponseEntity<>(new ApiResponse(true, response),OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception),BAD_REQUEST);
        }
    }

    @PatchMapping("/update-expenses")
    public ResponseEntity<?> updateExpenses(@RequestBody UpdateExpenseRequest request){
        try{
            UpdateExpenseResponse response = services.updateExpensesByExpenseType(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception),BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-Expenses/")
    public ResponseEntity<?> deleteExpenses(@RequestBody ExpenseType expenseType){
        try{
            DeleteExpenseResponse response = services.deleteExpensesByExpenseType(expenseType);
            return new ResponseEntity<>(new ApiResponse(true, response), OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception),BAD_REQUEST);
        }
    }

//    @GetMapping()
//    public ResponseEntity<?> getExpenses(@RequestBody ExpenseRequest request){
//        try{
//
//        }
    //}
}
