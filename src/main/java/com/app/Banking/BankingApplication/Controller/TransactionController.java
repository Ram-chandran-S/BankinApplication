package com.app.Banking.BankingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.DTO.TransactionType;
import com.app.Banking.BankingApplication.Service.TransactionService;


@RestController
@RequestMapping("Transaction")
public class TransactionController {
	@Autowired
	private TransactionService service;
	@PostMapping
	public ResponseEntity<Transaction> saveTransaction(@RequestParam int AccountId,@RequestParam String email,@RequestParam String possword, @RequestBody Transaction transaction) {
		return service.saveTransaction(AccountId,email, possword, transaction);
	}
	@GetMapping
	public ResponseEntity<Transaction> findTransactionById(@RequestParam int transactionId) {
		return service.findTransaction(transactionId);
	}
	@GetMapping("getAll")
	public ResponseEntity<List<Transaction>> findAllTransaction() {
		return service.findAllTransaction();
	}
	@GetMapping("getByType")
	public ResponseEntity<List<Transaction>> findTransactionByType(@RequestParam TransactionType type) {
		return service.findTransactionByType(type);
	}
	@PostMapping("upi")
	public ResponseEntity<Transaction> upiTransaction(@RequestParam int fromAccountId,@RequestParam int toAccount,@RequestParam String possword,@RequestBody Transaction transacion) {
		return service.UPItransaction(fromAccountId, toAccount, possword, transacion);
	}
	

}
