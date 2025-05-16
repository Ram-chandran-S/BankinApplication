package com.app.Banking.BankingApplication.DTO;

import java.util.List;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@Entity
@Cacheable
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@NotNull(message = "The field does not have null values")
	@NotBlank(message = "the field does not empty and not comtain only white space")
	private String customerName;
	@Email
	@NotNull(message = "The field does not have null values")
	@NotBlank(message = "the field does not empty and not comtain only white space")
	private String customerEmail;
	@Min(value = 6000000000l,message = "invalid contact")
	@Max(value = 9999999999l,message = "invalid contact")
	private long customerContact;
	@Min(value = 6000000000l,message = "invalid contact")
	@Max(value = 9999999999l,message = "invalid contact")
	@Size(min = 8)
	private String customerPossword;
	

}
