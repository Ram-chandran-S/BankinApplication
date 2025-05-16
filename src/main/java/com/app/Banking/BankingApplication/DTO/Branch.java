package com.app.Banking.BankingApplication.DTO;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@Entity
@Cacheable
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchId;
	@NotBlank(message = "the field does not empty and not comtain only white space")
	@NotNull(message = "The field does not have null values")
	private String branchName;
	
	private String branchIFSCcode;
	@Min(value = 6000000000l,message = "invalid contact")
	@Max(value = 9999999999l,message = "invalid contact")
	private long branchContact;
	@NotBlank(message = "the field does not empty and not comtain only white space")
	@NotNull(message = "The field does not have null values")
	private String branchCity;
	@OneToOne(cascade = CascadeType.ALL)
	private Bank branchBank;
	@OneToOne(cascade = CascadeType.ALL)
	private Manager branchManager;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Customer> branchCustomer;

}
