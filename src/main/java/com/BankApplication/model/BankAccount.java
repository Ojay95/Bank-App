package com.BankApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Entity(name = "accounts")

public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "account_user_id")
    private AccountUser accountUser;

    private double accountBalance;

    private String accountNumber;

    public BankAccount (){}


    public BankAccount( AccountUser accountUser, double accountBalance, String accountNumber) {
        this.accountUser = accountUser;
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountUser=" + accountUser +
                ", accountBalance=" + accountBalance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}