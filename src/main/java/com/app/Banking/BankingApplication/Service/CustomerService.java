package com.app.Banking.BankingApplication.Service;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Banking.BankingApplication.DAO.CustomerDAO;
import com.app.Banking.BankingApplication.DTO.Customer;
import com.app.Banking.BankingApplication.Exception.CustomerNotFoundException;

@Service
public class CustomerService {
	@Autowired
	private CustomerDAO dao;
	
	public ResponseEntity<Customer> saveCustomer(Customer customer) {
		return new ResponseEntity<Customer>(dao.saveCustomer(customer),HttpStatus.CREATED);
	}
	public ResponseEntity<Customer>	findCustomerById(int customerId){
		Customer dbCustomer = dao.findCustomerById(customerId);
		if(dbCustomer != null)
			return new ResponseEntity<Customer>(dbCustomer,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("Customer Not Found For given Id");
	}
	public ResponseEntity<Customer> updateCustomer(int oldCustomerId,Customer newdata) {
		Customer dbCustomer=dao.findCustomerById(oldCustomerId);
		if(dbCustomer != null)
			return new ResponseEntity<Customer>(dao.updateCustomer(oldCustomerId, newdata),HttpStatus.ACCEPTED);
		else throw new CustomerNotFoundException("Customer Not Found For given Id");
	}
	public ResponseEntity<List<Customer>> findAllCustomer() {
		List<Customer> list = dao.findAllCustomers();
		if(list!=null)
			return new ResponseEntity<List<Customer>>(list,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("No Data Found");
	}
	public ResponseEntity<Customer> deleteCustome(int customerId) {
		Customer dbCustomer = dao.findCustomerById(customerId);
		if(dbCustomer!=null)
			return new ResponseEntity<Customer>(dao.deleteCustomer(customerId),HttpStatus.GONE);
		else throw new CustomerNotFoundException("Customer Not found For Given Id");
	}
	public ResponseEntity<List<Customer>> findCustomerByName(String customerName) {
		List<Customer> dbdata = dao.findCustomerByName(customerName);
		if(dbdata != null) 
			return new ResponseEntity<List<Customer>>(dbdata,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("No Data found For "+customerName);

	}
	public ResponseEntity<Customer> findCustomerByEmailaddress(String email) {
		Customer dbcustomer= dao.findCustomerByEmailaddress(email);
		if(dbcustomer!=null)
			return new ResponseEntity<Customer>(dbcustomer,HttpStatus.FOUND);
		else throw new CustomerNotFoundException("Customer not found for this "+ email+" Address");
	}

}
