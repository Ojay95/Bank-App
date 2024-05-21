package com.BankApplication.repository;

import com.BankApplication.model.AccountUser;
import com.BankApplication.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    BankAccount findByAccountUser(AccountUser user);

    BankAccount findBankAccountByAccountNumber(String accountNumber);
}
