package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class DeleteExpenseRequest {
    @Id
    private String id;
    private String expenseType;
}
