package com.app.Banking.BankingApplication.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Banking.BankingApplication.DTO.Customer;
import com.app.Banking.BankingApplication.Repository.CustomerRepository;

@Repository
public class CustomerDAO {
	@Autowired
	private CustomerRepository repo;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
		
	}
	public Customer findCustomerById(int customerId) {
		Optional<Customer> opCus = repo.findById(customerId);
		if(opCus.isPresent())
			return opCus.get();
		else return null;
		
	}
	public Customer updateCustomer(int dbCustomerId,Customer newData) {
		Customer dbdata=findCustomerById(dbCustomerId);
		if(dbdata != null) {
			newData.setCustomerId(dbCustomerId);
			return repo.save(newData);
		}
		else return null;
	}
	public List<Customer> findAllCustomers() {
		List<Customer> liscus = repo.findAll();
		if (liscus!=null) {
			return liscus;
		}
		else return null;
	}
	public Customer deleteCustomer(int customerId) {
		Customer dbcus = findCustomerById(customerId);
		if (dbcus != null) {
			repo.delete(dbcus);
			return dbcus;
		}
		else return null;
	}	
	public Customer findCustomerByName(String customername) {
		Customer dbdata = repo.findByCustomerName(customername);
		if(dbdata != null) 
			return dbdata;
		else return null;
	}

}
