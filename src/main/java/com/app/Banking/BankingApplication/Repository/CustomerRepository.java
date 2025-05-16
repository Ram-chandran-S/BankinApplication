package com.app.Banking.BankingApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Banking.BankingApplication.DTO.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findByCustomerName(String customerString);
}
