package com.app.Banking.BankingApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.DTO.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	List<Transaction> findByTransactionsType(TransactionType type);
}
