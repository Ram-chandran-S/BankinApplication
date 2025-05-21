package com.app.Banking.BankingApplication.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Banking.BankingApplication.DAO.AccountDao;
import com.app.Banking.BankingApplication.DAO.CustomerDAO;
import com.app.Banking.BankingApplication.DTO.Account;
import com.app.Banking.BankingApplication.DTO.AccountType;
import com.app.Banking.BankingApplication.DTO.Customer;
import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.Exception.AccountNotFoundException;
import com.app.Banking.BankingApplication.Exception.CustomerNotFoundException;
import com.app.Banking.BankingApplication.Exception.TransactionNotFoundException;

@Service
public class AccountService {
	@Autowired
	private AccountDao dao;
	@Autowired
	private CustomerDAO cusdao;
	
	public ResponseEntity<Account> saveAccount(Account account) {
		return new ResponseEntity<Account>(dao.saveAccount(account),HttpStatus.CREATED);
	}
	public ResponseEntity<Account> findAccountbyId(int accountId) {
		Account dbAccount = dao.findAccountById(accountId);
		if(dbAccount != null)
			return new ResponseEntity<Account>(dbAccount,HttpStatus.FOUND);
		else throw new AccountNotFoundException("Account Not Fond for given Id");
	}
	public ResponseEntity<Account> updateAccount(int oldAccountId,Account newData) {
		Account dbAccount = dao.findAccountById(oldAccountId);
		if(dbAccount != null)
			return new ResponseEntity<Account>(dao.updateAccount(oldAccountId, newData),HttpStatus.FOUND);
		else throw new AccountNotFoundException("Account Not Fond for given Id");
	}
	public ResponseEntity<Account> deleteAccount(int accountId) {
		Account dbAccount = dao.findAccountById(accountId);
		if(dbAccount != null)
			return new ResponseEntity<Account>(dao.deleteAccount(accountId),HttpStatus.FOUND);
		else throw new AccountNotFoundException("Account Not Fond for given Id");
	}
	public ResponseEntity<List<Account>> findAllAccount() {
		List<Account> dbAccountlist=dao.findAllAccount();
		if(dbAccountlist != null)
			return new ResponseEntity<List<Account>>(dbAccountlist,HttpStatus.FOUND);
		else throw new AccountNotFoundException("No Data Found");
	}
	public ResponseEntity<List<Account>> findByAccountType(AccountType type) {
		List<Account> dbAccountlist = dao.findByAccountType(type);
		if(dbAccountlist != null)
			return new ResponseEntity<List<Account>>(dbAccountlist,HttpStatus.FOUND);
		else throw new AccountNotFoundException("No data found");
	}
	public ResponseEntity<List<Transaction>> findTransactionByAccountId(int accountId) {
		Account dbaccount = dao.findAccountById(accountId);
		if(dbaccount != null)
			return new ResponseEntity<List<Transaction>>(dao.findTransactionByAccountId(accountId),HttpStatus.FOUND);
		else throw new TransactionNotFoundException("there is no transaction for "+ dbaccount.getAccountCustomer().getCustomerName()+" User");
	}
	public ResponseEntity<Account> addRelationAccountToCustomerById(int AccId,int customerId) {
		Account dbacc=dao.findAccountById(AccId);
		Customer dbcustomer = cusdao.findCustomerById(customerId);
		if (dbacc !=null) {
			if(dbcustomer != null) {
				return new ResponseEntity<Account>(dao.addRelationAccountToCustomerById(AccId, customerId),HttpStatus.OK);
			}
			else throw new CustomerNotFoundException("No Customer for given Id");
		} else throw new AccountNotFoundException("Account Not found for given Id");
		
	}

}
