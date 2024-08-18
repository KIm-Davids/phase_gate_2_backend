package com.semicolon.africa.dtos.response;

import com.semicolon.africa.models.Expenses;
import com.semicolon.africa.models.Income;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfitResponse {

//    private Income profit;
    private String message;
    private String email;
    private String netAmount;
    private LocalDateTime localDateTime;
    private List<Income> income;
    private List<Expenses> expenses;
    private String advice;
}
