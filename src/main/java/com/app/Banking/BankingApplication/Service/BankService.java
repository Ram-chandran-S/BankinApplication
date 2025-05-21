package com.app.Banking.BankingApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Banking.BankingApplication.DAO.BankDAO;
import com.app.Banking.BankingApplication.DAO.BranchDAO;
import com.app.Banking.BankingApplication.DTO.Bank;
import com.app.Banking.BankingApplication.DTO.Branch;
import com.app.Banking.BankingApplication.Exception.BankNotFoundException;
import com.app.Banking.BankingApplication.Exception.BranchNotFoundExceotion;

@Service
public class BankService {
	@Autowired
	private BankDAO dao;
	@Autowired
	private BranchDAO branchdao;
	
	public ResponseEntity<Bank> saveBank(Bank bank) {
		return new ResponseEntity<Bank>(dao.saveBank(bank),HttpStatus.CREATED);
	}
	
	public ResponseEntity<Bank> findBankById(int bankId) {
		Bank dbBank = dao.findBankById(bankId);
		if(dbBank != null )
			return new ResponseEntity<Bank>(dbBank,HttpStatus.FOUND);
		else 
			throw new BankNotFoundException("Bnak not Found for given Id");
	}
	public ResponseEntity<List<Bank>> findAllBank() {
		List<Bank> lsBank = dao.findAllBank();
		if(lsBank != null)
			return new ResponseEntity<List<Bank>>(lsBank,HttpStatus.FOUND);
		else
			throw new BankNotFoundException("there Is No Bank in DataBase");
	}
	
	public ResponseEntity<List<Bank>> findBankByName(String BankName) {
		List<Bank> dbbank = dao.findBankByName(BankName);
		if(dbbank != null) 
			return new ResponseEntity<List<Bank>>(dbbank,HttpStatus.FOUND);
		else throw new BankNotFoundException("Bank Not Fount for given name");
	}
	public ResponseEntity<Bank> addRelationBankToBranchById(int bankId,int BranchId) {
		Bank dbBank = dao.findBankById(bankId);
		Branch dbBranch =branchdao.findBranchById(BranchId);
		if(dbBranch != null) {
			if(dbBank != null) {
				return new ResponseEntity<Bank>(dao.addRelationBankToBranchById(bankId, BranchId),HttpStatus.OK);
			}else throw new BankNotFoundException("Bank not found for Given Id");
		}else throw new BranchNotFoundExceotion("Branch Not found for given Id");

}}
