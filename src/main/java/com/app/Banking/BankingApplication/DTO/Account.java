package com.app.Banking.BankingApplication.DTO;

import java.util.List;

import org.hibernate.annotations.Check;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	@DecimalMin(value = "5000.0", message = "Minimum balance should be 5000")
	private double accountBalance;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	@OneToOne(cascade = CascadeType.ALL)
	private Customer accountCustomer;
	@OneToMany(mappedBy = "transactionAccount",cascade = CascadeType.ALL)
	private List<Transaction> accountTransactions;
	
	
	public Account(@DecimalMin(value = "5000.0", message = "Minimum balance should be 5000") double accountBalance,
			AccountType accountType) {
		super();
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}
	
	

}
