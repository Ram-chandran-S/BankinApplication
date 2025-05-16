package com.app.Banking.BankingApplication.Exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> bankNotFoundException(BankNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> branchNotFoundException(BranchNotFoundExceotion ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> managerNotFounfdException(ManagerNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> customerNotFoundException(CustomerNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> accountNotFoundException(AccountNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> transactionNotFoundException(TransactionNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> data = new LinkedHashMap<String,String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message=violation.getMessage();
			data.put(field, message);
		}
		return new ResponseEntity<Map<String,String>>(data,HttpStatus.FORBIDDEN);
	}

}
