package com.BankApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity(name ="transactions")
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotBlank
    @Length(min = 10)
    private String accountNumber;

    private String transactionDate;

    private double amount;

    private  String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
