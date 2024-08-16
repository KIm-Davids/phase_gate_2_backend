package com.semicolon.africa.dtos.response;

import com.semicolon.africa.constants.ExpenseType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateExpenseResponse {

   private String message;
}
