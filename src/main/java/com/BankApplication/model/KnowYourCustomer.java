package com.BankApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "kyc")
public class KnowYourCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bankVerificationNumber;
    private String nin;
    private String address;
    private String localGovtOfResidence;
    private String stateOfResidence;
    private String dateOfBirth;
    private String nextOfKin;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AccountUser user;

    public KnowYourCustomer(){}
}