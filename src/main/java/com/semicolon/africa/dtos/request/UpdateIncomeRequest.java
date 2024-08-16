package com.semicolon.africa.dtos.request;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.constants.IncomeType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateIncomeRequest {

    @Id
    private String id;
    private LocalDateTime newLocalDateTime;
    private String newDescription;
    private IncomeType newIncomeType;
    private double newTotalIncomeAmount;
}
