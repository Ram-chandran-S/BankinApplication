package com.app.Banking.BankingApplication.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Banking.BankingApplication.DTO.Bank;
import com.app.Banking.BankingApplication.Repository.BankRepository;

@Repository
public class BankDAO {
	@Autowired
	private BankRepository repo;
	
	public Bank saveBank(Bank bank) {
		return repo.save(bank);
	}
	public Bank findBAnkById(int bankId) {
		Optional<Bank> opbank= repo.findById(bankId);
		if(opbank.isPresent()) 
			return opbank.get();
		else return null;
	}
	public List<Bank> findAllEmployees(){
		List<Bank> lisBank = repo.findAll();
		if(lisBank !=null)
		return lisBank;
		else return null;
	}
	public List<Bank> findBankByName(String bankName) {
		List<Bank> lisBank= repo.findByBankName(bankName);
		if(lisBank !=null)
		return lisBank;
		else return null;
	}
	

}
