package com.BankApplication.model;

import com.BankApplication.service.AccountUserService;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private AccountUser user;
    private String token;
}