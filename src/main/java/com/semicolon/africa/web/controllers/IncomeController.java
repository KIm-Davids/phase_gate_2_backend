package com.semicolon.africa.web.controllers;

import com.semicolon.africa.constants.IncomeType;
import com.semicolon.africa.dtos.request.IncomeRequest;
import com.semicolon.africa.dtos.request.ProfitRequest;
import com.semicolon.africa.dtos.request.UpdateIncomeRequest;
import com.semicolon.africa.dtos.response.ApiResponse;
import com.semicolon.africa.dtos.response.DeleteIncomeResponse;
import com.semicolon.africa.dtos.response.IncomeResponse;
import com.semicolon.africa.dtos.response.UpdateIncomeResponse;
import com.semicolon.africa.services.IncomeServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/income")
@AllArgsConstructor
public class IncomeController {

    private IncomeServices services;

    @PostMapping("/add-income")
    public ResponseEntity<?> addIncome(@RequestBody IncomeRequest request){
        try{
            IncomeResponse response = services.addIncomeToRepository(request);
            return new ResponseEntity<>(new ApiResponse(true, response), OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception),BAD_REQUEST);
        }
    }

    @PatchMapping("/update-income")
    public ResponseEntity<?> updateIncome(@RequestBody UpdateIncomeRequest request){
        try{
            UpdateIncomeResponse response = services.updateIncomeById(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception), BAD_GATEWAY);
        }
    }

    @DeleteMapping("/delete-income")
    public ResponseEntity<?> deleteIncome(@RequestBody IncomeType request){
        try{
            DeleteIncomeResponse response = services.deleteIncomeByIncomeType(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception), BAD_GATEWAY);
        }
    }

//    @GetMapping
//    public ResponseEntity<?> getProfitAmount(@RequestBody ){
//
//    }
}
