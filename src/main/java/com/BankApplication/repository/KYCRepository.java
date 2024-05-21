package com.BankApplication.repository;

import com.BankApplication.model.AccountUser;
import com.BankApplication.model.KnowYourCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCRepository extends JpaRepository<KnowYourCustomer, Integer> {
    KnowYourCustomer getByUser (AccountUser user);

}
