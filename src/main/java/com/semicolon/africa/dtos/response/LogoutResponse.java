package com.semicolon.africa.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponse {

    private String message;
    private boolean isLoggedIn;
}
