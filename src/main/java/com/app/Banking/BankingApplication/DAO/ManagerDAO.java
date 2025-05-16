package com.app.Banking.BankingApplication.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Banking.BankingApplication.DTO.Manager;
import com.app.Banking.BankingApplication.Repository.ManagerRepository;

@Repository
public class ManagerDAO {
	@Autowired
	private ManagerRepository repo;
	
	public Manager saveManager(Manager manager) {
		return repo.save(manager);
		
	}
	public Manager findManagerById(int id) {
		Optional<Manager> opman = repo.findById(id);
		if (opman.isPresent()) 
			return opman.get();
		else return null;
		
	}
	public Manager updateManager(int dbManagerId,Manager newData) {
		Manager dbdata = findManagerById(dbManagerId);
		if(dbdata != null) {
			newData.setManagerId(dbManagerId);
			return repo.save(newData);
		}
		else return null;
	}
	public List<Manager> findAllBranch() {
		List<Manager> manlist= repo.findAll();
		if (manlist != null) 
			return manlist;
			
		else 
			return null;
	}
	public List<Manager> findByManagerName(String managerName) {
		List<Manager> manlist=repo.findByName(managerName);
		if (manlist != null) 
			return manlist;
		else 
			return null;
	}
	


}
