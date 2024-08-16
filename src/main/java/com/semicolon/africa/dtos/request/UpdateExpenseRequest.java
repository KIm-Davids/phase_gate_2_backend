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
public class UpdateExpenseRequest {

    @Id
    private String newId;
    private LocalDateTime newLocalDateTime;
    private String newDescription;
    private ExpenseType newExpenseType;
    private double newTotalExpenseAmount;

}
