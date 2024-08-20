package com.semicolon.africa.models;

import com.semicolon.africa.constants.ExpenseType;
import com.semicolon.africa.constants.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Income {
    @Id
    private String id;
    private IncomeType incomeType;
    private double totalIncome;
    private String description;
    private LocalDateTime localDateTime;
//    private List<Income> listOfIncome;

}
