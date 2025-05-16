package com.app.Banking.BankingApplication.DTO;

import java.util.List;

import org.hibernate.annotations.Check;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "accountBalance >= 5000")
@Cacheable
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	@Positive(message = "balance should not negative")
	private double accountBalance;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	@OneToOne(cascade = CascadeType.ALL)
	private Customer accountCustomer;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Transaction> accountTransactions;

}
