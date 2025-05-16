package com.app.Banking.BankingApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Banking.BankingApplication.DTO.Account;
import com.app.Banking.BankingApplication.DTO.AccountType;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	List<Account> findByAccountType(AccountType type);
}
