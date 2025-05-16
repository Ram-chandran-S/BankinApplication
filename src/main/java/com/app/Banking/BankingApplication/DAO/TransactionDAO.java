package com.app.Banking.BankingApplication.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.Banking.BankingApplication.DTO.Account;
import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.DTO.TransactionType;
import com.app.Banking.BankingApplication.Repository.AccountRepository;
import com.app.Banking.BankingApplication.Repository.TransactionRepository;

public class TransactionDAO {
	@Autowired
	private TransactionRepository repo;
	@Autowired
	private AccountDao dao;
	
	
	public Transaction saveTransaction(Transaction transaction) {
		if(transaction.getTransactionType()==TransactionType.CREDIT)
			return selfcredit(transaction);
		else
			return selfDebit(transaction);
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
		List<Transaction> tranlist= repo.findByTransactionsType(type);
		if (tranlist != null) 
			return tranlist;
		else return null;
	}
	public Transaction selfcredit(Transaction data) {
		Account dbdata=dao.findAccountById(data.getTransactionAccount().getAccountId());
		if(dbdata != null) {
			double bal=dbdata.getAccountBalance()+data.getTransactionAmount();
			dbdata.setAccountBalance(bal);
			return saveTransaction(data);
		}
		else return null;
	}
	public Transaction selfDebit(Transaction data) {
		Account dbdata=dao.findAccountById(data.getTransactionAccount().getAccountId());
		if(dbdata != null) {
			if(dbdata.getAccountBalance()>=data.getTransactionAmount()) {
				dbdata.setAccountBalance(dbdata.getAccountBalance()-data.getTransactionAmount());
				return saveTransaction(data);
			}
			else return null;
		}
		
		else return null;
	}
	
	public Transaction upiTransaction(int toAccountId,Transaction data) {
		var toAccount=dao.findAccountById(toAccountId);
		var fromAccount = dao.findAccountById(data.getTransactionAccount().getAccountId());
		if (fromAccount != null) {
			if(toAccount != null) {
			if(fromAccount.getAccountBalance()>=data.getTransactionAmount()) {
				fromAccount.setAccountBalance(fromAccount.getAccountBalance()-data.getTransactionAmount());
				toAccount.setAccountBalance(toAccount.getAccountBalance()+data.getTransactionAmount());
				data.setTransactionType(TransactionType.DEBIT);
				var toAccountTransaction=data;
				toAccountTransaction.setTransactionType(TransactionType.CREDIT);
				toAccountTransaction.setTransactionAccount(toAccount);
				saveTransaction(toAccountTransaction);
				return saveTransaction(data);
			}else return null;
			}else return null;
			}else return null;
	}

}
