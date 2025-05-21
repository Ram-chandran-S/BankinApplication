package com.app.Banking.BankingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Banking.BankingApplication.DTO.Customer;
import com.app.Banking.BankingApplication.Service.CustomerService;


@RestController
@RequestMapping("Customer")
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	@GetMapping
	public ResponseEntity<Customer> findCustomerById(@RequestParam int customerId) {
		return service.findCustomerById(customerId);
	}
	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestParam int oldId,@RequestBody Customer newData) {
		return service.updateCustomer(oldId, newData);
	}
	@GetMapping("getAll")
	public ResponseEntity<List<Customer>> findAllCustomer() {
		return service.findAllCustomer();
	}
	@DeleteMapping
	public ResponseEntity<Customer> deleteCustomer(@RequestParam int customerId) {
		return service.deleteCustome(customerId);
	}
	@GetMapping("byName")
	public ResponseEntity<List<Customer>> findCustomerByName(@RequestParam String customerName) {
		return service.findCustomerByName(customerName);
	}
	@GetMapping("findCustomerByEmailaddress")
	public ResponseEntity<Customer> findCustomerByEmailaddress(@RequestParam String email) {
		return service.findCustomerByEmailaddress(email);
	}

}
