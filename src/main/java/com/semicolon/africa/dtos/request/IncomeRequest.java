package com.semicolon.africa.dtos.request;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.constants.IncomeType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class IncomeRequest {

    @Id
    private String id;
    private String description;
    private IncomeType incomeType;
    private double totalIncomeAmount;
    private LocalDateTime localDateTime;
}
