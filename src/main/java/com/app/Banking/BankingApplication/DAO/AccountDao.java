package com.app.Banking.BankingApplication.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Banking.BankingApplication.DTO.Account;
import com.app.Banking.BankingApplication.DTO.AccountType;
import com.app.Banking.BankingApplication.DTO.Customer;
import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.Repository.AccountRepository;
@Repository
public class AccountDao {
	@Autowired
	private AccountRepository repo;
	@Autowired
	private TransactionDAO dao;
	@Autowired
	private CustomerDAO cusdao;
	
	
	public Account saveAccount(Account account) {
		return repo.save(account);
	}
	public Account findAccountById(int accountId) {
		Optional<Account> opacc=repo.findById(accountId);
		if (opacc.isPresent()) {
			return opacc.get();
		} else {
		return null;
		}
	}
	public Account addRelationAccountToCustomerById(int accountId,int customerId) {
		Account dbacc = findAccountById(accountId);
		Customer dbcustomer = cusdao.findCustomerById(customerId);
		if (dbacc !=null) {
			if(dbcustomer != null) {
				dbacc.setAccountCustomer(dbcustomer);
				return saveAccount(dbacc);
			}
			else return null;
		} else return null;
	}
	public  Account updateAccount(int oldAccId,Account newdata) {
		Account dbdata = findAccountById(oldAccId);
		if (dbdata != null) {
			newdata.setAccountId(oldAccId);
			return repo.save(newdata);
		}
		else return null;
	}
	public Account  deleteAccount(int accountTd) {
		Account dbdata = findAccountById(accountTd);
		if (dbdata != null) {
			repo.delete(dbdata);
			return dbdata;
		}
		else return null;
	}
	public List<Account> findAllAccount() {
		List<Account> lisacc = repo.findAll();
		if (lisacc!=null) {
			return lisacc;
		}
		else return null;
	}
	public List<Account> findByAccountType(AccountType type) {
		List<Account> lisacc = repo.findByAccountType(type);
		if (lisacc!=null) {
			return lisacc;
		}
		else return null;
		
	}
	public List<Transaction> findTransactionByAccountId(int accountId) {
		Account dbaccount = findAccountById(accountId);
		if(dbaccount != null) {
			return dbaccount.getAccountTransactions();
		}
		else return null;
	}
	

}
