package com.app.Banking.BankingApplication.DTO;

import java.util.List;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
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
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	@NotNull(message = "The field does not have null values")
	@NotBlank(message = "the field does not empty and not comtain only white space")
	private String managerName;
	@Email
	@NotNull(message = "The field does not have null values")
	@NotBlank(message = "the field does not empty and not comtain only white space")
	private String managerEmail;
	@NotNull(message = "The field does not have null values")
	@NotBlank(message = "the field does not empty and not comtain only white space")
	private String managerPassword;
	@Min(value = 6000000000l,message = "invalid contact")
	@Max(value = 9999999999l,message = "invalid contact")
	private long managerContact;
	@OneToOne(cascade = CascadeType.ALL)
	private Branch managerBranch;
	
	
	 

}
