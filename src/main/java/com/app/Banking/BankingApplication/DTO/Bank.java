package com.app.Banking.BankingApplication.DTO;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.springframework.cache.annotation.Cacheable;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Entity
@Cacheable
@NoArgsConstructor
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	@NotBlank(message = "the field does not empty and not comtain only white space")
	@NotNull(message = "The field does not have null values")
	private String bankName;
	@NotBlank(message = "the field does not empty and not comtain only white space")
	@NotNull(message = "The field does not have null values")
	private String bankHeadOffice;
	@OneToOne(cascade = CascadeType.ALL)
	private Branch bankBranch;
	public Bank(
			@NotBlank(message = "the field does not empty and not comtain only white space") @NotNull(message = "The field does not have null values") String bankName,
			@NotBlank(message = "the field does not empty and not comtain only white space") @NotNull(message = "The field does not have null values") String bankHeadOffice) {
		super();
		this.bankName = bankName;
		this.bankHeadOffice = bankHeadOffice;
	}
	
	

}
