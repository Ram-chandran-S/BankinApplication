package com.app.Banking.BankingApplication.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BranchNotFoundExceotion extends RuntimeException {
	private String message;
}
