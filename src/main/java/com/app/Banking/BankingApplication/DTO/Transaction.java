package com.app.Banking.BankingApplication.DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@Entity
@Cacheable
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@ManyToOne(cascade = CascadeType.ALL)
	private Account transactionAccount;
	@Min(value=1,message = "Atleast send 1 Rs")
	private double transactionAmount;
	private LocalDateTime transactionDate;
	
	@PrePersist
    protected void fetchDate() {
        this.transactionDate = LocalDateTime.now();
	}
}



