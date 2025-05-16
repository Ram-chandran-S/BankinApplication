package com.app.Banking.BankingApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Banking.BankingApplication.DAO.AccountDao;
import com.app.Banking.BankingApplication.DAO.TransactionDAO;
import com.app.Banking.BankingApplication.DTO.Account;
import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.DTO.TransactionType;
import com.app.Banking.BankingApplication.Exception.TransactionNotFoundException;

@Service
public class TransactionService {
	@Autowired
	private TransactionDAO dao;
	@Autowired
	private AccountDao accDao;
	
	public ResponseEntity<Transaction> saveTransaction(Transaction transaction) {
		return new ResponseEntity<Transaction>(dao.saveTransaction(transaction),HttpStatus.CREATED);
	}
	public ResponseEntity<Transaction> findTransaction(int transactionId) {
		Transaction tran = dao.findTransaction(transactionId);
		if(tran!=null)
			return new ResponseEntity<Transaction>(tran,HttpStatus.FOUND);
		else throw new TransactionNotFoundException("transaction Not found For given ID");
	}
	public ResponseEntity<List<Transaction>> findAllTransaction() {
		List<Transaction> dblist=dao.findAllTransaction();
		if(dblist!=null)
			return new ResponseEntity<List<Transaction>>(dblist,HttpStatus.FOUND);
		else throw new TransactionNotFoundException("No transaction Found");
	}
	public ResponseEntity<List<Transaction>> findTransactionByType(TransactionType type) {
		List<Transaction> dbTran=dao.findByTransactionType(type);
		if(dbTran != null)
			return new ResponseEntity<List<Transaction>>(dbTran,HttpStatus.FOUND);
		else throw new TransactionNotFoundException("No Transaction Found For this Type");
	}
	public ResponseEntity<Transaction> UPItransaction(int toAccountId,Transaction data) {
		var toAccount=accDao.findAccountById(toAccountId);
		var fromAccount = accDao.findAccountById(data.getTransactionAccount().getAccountId());
		if (fromAccount != null) {
			if(toAccount != null) {
			if(fromAccount.getAccountBalance()>=data.getTransactionAmount()) {
				return new ResponseEntity<Transaction>(dao.upiTransaction(toAccountId, data),HttpStatus.ACCEPTED);
			}else throw new TransactionNotFoundException("Insufficent balance ");
			}else throw new TransactionNotFoundException("reciever account not found ");
		}else throw new TransactionNotFoundException("your account not found ");
	}
	
	
}
