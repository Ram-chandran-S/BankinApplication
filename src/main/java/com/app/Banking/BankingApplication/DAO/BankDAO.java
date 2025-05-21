package com.app.Banking.BankingApplication.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Banking.BankingApplication.DTO.Bank;
import com.app.Banking.BankingApplication.DTO.Branch;
import com.app.Banking.BankingApplication.Repository.BankRepository;

@Repository
public class BankDAO {
	@Autowired
	private BankRepository repo;
	@Autowired
	private BranchDAO branchdao;
	
	
	public Bank saveBank(Bank bank) {
		return repo.save(bank);
	}
	public Bank findBankById(int bankId) {
		Optional<Bank> opbank= repo.findById(bankId);
		if(opbank.isPresent()) 
			return opbank.get();
		else return null;
	}
	public List<Bank> findAllBank(){
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
	public Bank addRelationBankToBranchById(int bankId,int BranchId) {
		Bank dbBank = findBankById(bankId);
		Branch dbBranch = branchdao.findBranchById(BranchId);
		if(dbBranch != null) {
			if(dbBank != null) {
				dbBank.setBankBranch(dbBranch);
				return saveBank(dbBank);
			}else return null;
		}else return null;
			
	}
	

}
