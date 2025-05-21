package com.app.Banking.BankingApplication.DAO;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.app.Banking.BankingApplication.DTO.Account;
import com.app.Banking.BankingApplication.DTO.Customer;
import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.DTO.TransactionType;
import com.app.Banking.BankingApplication.Exception.AccountNotFoundException;
import com.app.Banking.BankingApplication.Exception.CustomerNotFoundException;
import com.app.Banking.BankingApplication.Repository.AccountRepository;
import com.app.Banking.BankingApplication.Repository.TransactionRepository;
@Repository
public class TransactionDAO {
	@Autowired
	private TransactionRepository repo;
	@Autowired
	@Lazy
	private AccountDao dao;
	@Autowired
	private CustomerDAO customerdao;
	
	private Transaction saveTransaction(Transaction transaction) {
		if(transaction.getTransactionType()==TransactionType.CREDIT)
			return selfcredit(transaction);
		else
			return selfDebit(transaction);
	}
	public Transaction validatecredential(int fromAccountId,String customerEmail,String CustomerPossword,Transaction transaction) {
		Customer dbCustomer = customerdao.findCustomerByEmailaddress(customerEmail);
		Account dbAccount = dao.findAccountById(fromAccountId);
		if(dbAccount != null) {
		if (dbCustomer != null) {
			if(dbCustomer.getCustomerPossword().equals(CustomerPossword)) {
				transaction.setTransactionAccount(dbAccount);
				return saveTransaction(transaction);
			}else throw new CustomerNotFoundException("Possword Missmatched");
			
		}else throw new CustomerNotFoundException("Customer not found for given Email");
		}else throw new AccountNotFoundException("Account Not found for given Id");
	}
	public Transaction findTransaction(int transactionId) {
		Optional<Transaction> optran= repo.findById(transactionId);
		if (optran.isPresent()) {
			return optran.get();
		}
		else return null;
		
	}
	public List<Transaction> findAllTransaction() {
		List<Transaction> tranlist= repo.findAll();
		if (tranlist != null) 
			return tranlist;
		else 
			return null;
	}
	
	public List<Transaction> findByTransactionType(TransactionType type) {
		List<Transaction> tranlist= repo.findByTransactionType(type);
		if (tranlist != null) 
			return tranlist;
		else return null;
	}
	public Transaction selfcredit(Transaction data) {
		Account dbdata=data.getTransactionAccount();
		if(dbdata != null) {
			double bal=dbdata.getAccountBalance()+data.getTransactionAmount();
			dbdata.setAccountBalance(bal);
			return repo.save(data);
		}
		else return null;
	}
	public Transaction selfDebit(Transaction data) {
		Account dbdata=dao.findAccountById(data.getTransactionAccount().getAccountId());
		if(dbdata != null) {
			if(dbdata.getAccountBalance()>=data.getTransactionAmount()) {
				dbdata.setAccountBalance(dbdata.getAccountBalance()-data.getTransactionAmount());
				return repo.save(data);
			}
			else return null;
		}
		
		else return null;
	}
	
	public Transaction upiTransaction(int fromAccountId,int toAccountId,String password,Transaction data) {
		var toAccount=dao.findAccountById(toAccountId);
		var fromAccount = dao.findAccountById(fromAccountId);
		var toAccountTransaction=data;
		if(fromAccount.getAccountCustomer().getCustomerPossword().equals(password)) {
			if (fromAccount != null) {
				data.setTransactionAccount(fromAccount);
				if(toAccount != null) {
					toAccountTransaction.setTransactionAccount(toAccount);
				if(fromAccount.getAccountBalance()>=data.getTransactionAmount()) {
					fromAccount.setAccountBalance(fromAccount.getAccountBalance()-data.getTransactionAmount());
					data.setTransactionType(TransactionType.DEBIT);
					var fromtrandata= repo.save(data);
					//to account
					toAccountTransaction.setTransactionType(TransactionType.CREDIT);
					toAccount.setAccountBalance(toAccount.getAccountBalance()+data.getTransactionAmount());
					toAccountTransaction.setTransactionAccount(toAccount);
					repo.save(toAccountTransaction);
					return fromtrandata;
				}else return null;
				}else return null;
				}else return null;
		}else throw new CustomerNotFoundException("Possword miss Matched");
	}

}
