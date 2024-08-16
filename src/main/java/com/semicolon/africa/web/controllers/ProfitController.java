package com.semicolon.africa.web.controllers;

import com.semicolon.africa.dtos.request.ExpenseRequest;
import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.dtos.response.*;
import com.semicolon.africa.services.ExpenseServices;
import com.semicolon.africa.services.IncomeServices;
import com.semicolon.africa.services.ProfitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/profitAmount")
@AllArgsConstructor
public class ProfitController {

    private IncomeServices incomeServices;
    private ExpenseServices expenseServices;
    private ProfitService profitService;

    @GetMapping("/get-profit")
    public ResponseEntity<?> getIncome(@RequestBody ProfitRequest profitRequest) {
        try {
            ProfitResponse profitResponse = profitService.calculateProfit(profitRequest);
            return new ResponseEntity<>(new ApiResponse(true, profitResponse), OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception), BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteExpenses")
    public ResponseEntity<?> deleteValues(){
        try{
            DeleteAllResponse expenseResponse = expenseServices.deleteAllExpenses();
            DeleteAllResponse incomeResponse = incomeServices.deleteAllIncome();
            DeleteAllResponse profitResponse = profitService.deleteAllResponse();
            return new ResponseEntity<>(new ApiResponse(true, expenseResponse), OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception), BAD_GATEWAY);
        }
    }
}