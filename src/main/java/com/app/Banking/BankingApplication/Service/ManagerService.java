package com.app.Banking.BankingApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Banking.BankingApplication.DAO.ManagerDAO;
import com.app.Banking.BankingApplication.DTO.Manager;
import com.app.Banking.BankingApplication.Exception.ManagerNotFoundException;

@Service
public class ManagerService {
	@Autowired
	private ManagerDAO dao;
	
	public ResponseEntity<Manager> saveManager(Manager manager) {
		return new ResponseEntity<Manager>(dao.saveManager(manager),HttpStatus.CREATED);
	}
	public ResponseEntity<Manager> findManagerById(int managerId) {
		Manager dbManager = dao.findManagerById(managerId);
		if(dbManager != null)
			return new ResponseEntity<Manager>(dbManager,HttpStatus.FOUND);
		else throw new ManagerNotFoundException("Manager Not Found for Given Id");
	}
	public ResponseEntity<Manager> updateManager(int oldManagerId,Manager newData) {
		Manager dbManager = dao.findManagerById(oldManagerId);
		if(dbManager != null)
			return new ResponseEntity<Manager>(dao.updateManager(oldManagerId, newData),HttpStatus.ACCEPTED);
		else throw new ManagerNotFoundException("Manager Not Found For Given Id");
	}
	public ResponseEntity<List<Manager>> findAllManager() {
		List<Manager> dbManagerlist = dao.findAlManager();
		if(dbManagerlist != null)
			return new ResponseEntity<List<Manager>>(dbManagerlist,HttpStatus.ACCEPTED);
		else throw new ManagerNotFoundException("There is no Manager");
	}
	public ResponseEntity<List<Manager>> findManagerByName(String managerName) {
		List<Manager> dbManager = dao.findByManagerName(managerName);
		if(dbManager != null)
			return new ResponseEntity<List<Manager>>(dbManager,HttpStatus.ACCEPTED);
		else throw new ManagerNotFoundException("Manager Not Found For Ginven name");
	}
	

}
