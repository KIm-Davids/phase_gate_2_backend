package com.semicolon.africa.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class Profit {

    @Id
    private String id;
    private String netProfit;
    private String netLoss;
    private LocalDateTime localDateTime;

}
