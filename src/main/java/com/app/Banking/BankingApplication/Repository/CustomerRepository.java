package com.app.Banking.BankingApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Banking.BankingApplication.DTO.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public List<Customer> findByCustomerName(String customerString);
	Customer findByCustomerEmail(String email);
}
