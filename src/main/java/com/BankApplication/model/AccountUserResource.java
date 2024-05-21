package com.BankApplication.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class AccountUserResource extends RepresentationModel<AccountUserResource> {
    private AccountUser accountUser;

}

