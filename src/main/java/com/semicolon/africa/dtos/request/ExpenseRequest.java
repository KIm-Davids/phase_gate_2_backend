package com.semicolon.africa.dtos.request;

import com.semicolon.africa.constants.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExpenseRequest {

    @Id
    private String id;
    private String description;
    private ExpenseType expenseType;
    private double totalExpenseAmount;
    private LocalDateTime localDateTime;


}
