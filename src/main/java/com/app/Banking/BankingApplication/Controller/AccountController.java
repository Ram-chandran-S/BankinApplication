package com.app.Banking.BankingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Banking.BankingApplication.DTO.Account;
import com.app.Banking.BankingApplication.DTO.AccountType;
import com.app.Banking.BankingApplication.DTO.Transaction;
import com.app.Banking.BankingApplication.Service.AccountService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("Account")
public class AccountController {
	@Autowired
	private AccountService service;
	@PostMapping
	public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
		return service.saveAccount(account);
	}
	@GetMapping
	public ResponseEntity<Account> findAccountById(@RequestParam int accountId) {
		return service.findAccountbyId(accountId);
	}
	@PutMapping
	public ResponseEntity<Account> updateAccount(@RequestParam int oldId,@RequestBody Account account) {
		return service.updateAccount(oldId, account);
	}
	@PutMapping("addRelationAccountToCustomerById")
	public ResponseEntity<Account> addRelationAccountToCustomerById(@RequestParam int accountId,@RequestParam int customerId) {
		return service.addRelationAccountToCustomerById(accountId, customerId);
	}
	@DeleteMapping
	public ResponseEntity<Account> deleteAccount(@RequestParam int accountId) {
		return service.deleteAccount(accountId);
	}
	@GetMapping("getAll")
	public ResponseEntity<List<Account>> findAllAccount() {
		return service.findAllAccount();
	}
	@GetMapping("getByType")
	public ResponseEntity<List<Account>> findAccountByType(@RequestParam AccountType type) {
		return service.findByAccountType(type);
	}
	@GetMapping("getTransactionByAccount")
	public ResponseEntity<List<Transaction>> findTransactionByAccountId(@RequestParam int accountId) {
		return service.findTransactionByAccountId(accountId);
	}
	
	

}
