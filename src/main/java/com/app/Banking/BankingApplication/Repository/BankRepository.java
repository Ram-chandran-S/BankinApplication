package com.app.Banking.BankingApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Banking.BankingApplication.DTO.Bank;

public interface BankRepository extends JpaRepository< Bank, Integer> {
	List<Bank> findByBankName(String name);
}
