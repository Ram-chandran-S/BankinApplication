package com.app.Banking.BankingApplication.DTO;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@Cacheable
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Account transactionAccount;
	@Min(value=1,message = "Atleast send 1 Rs")
	private double transactionAmount;
	private LocalDateTime transactionDate;
	
	
	
	@PrePersist
    protected void fetchDate() {
        this.transactionDate = LocalDateTime.now();
	}



	public Transaction(TransactionType transactionType,
			@Min(value = 1, message = "Atleast send 1 Rs") double transactionAmount) {
		super();
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}
	
	
	
}



