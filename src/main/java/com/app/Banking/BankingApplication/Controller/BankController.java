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

import com.app.Banking.BankingApplication.DTO.Bank;
import com.app.Banking.BankingApplication.Service.BankService;


@RestController
@RequestMapping("bank")
public class BankController {
	@Autowired
	private BankService service;
	@PostMapping
	public ResponseEntity<Bank> saveBank(@RequestBody Bank bank) {
		return service.saveBank(bank);
	}
	@GetMapping
	public ResponseEntity<Bank> findBankById(@RequestParam int bankId) {
		return service.findBankById(bankId);
	}
	@GetMapping("getAll")
	public ResponseEntity<List<Bank>> findAllBank() {
		return service.findAllBank();
	}
	@GetMapping("getByName")
	public ResponseEntity<List<Bank>> findBankByName(@RequestParam String bankName) {
		return service.findBankByName(bankName);
	}
	@GetMapping("addRelationBankToBranchById")
	public ResponseEntity<Bank> addRelationBankToBranchById(@RequestParam int bankId,@RequestParam int BranchId) {
		return service.addRelationBankToBranchById(bankId, BranchId);
	}
}
