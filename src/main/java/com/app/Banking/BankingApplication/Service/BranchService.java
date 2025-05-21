package com.app.Banking.BankingApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.Banking.BankingApplication.DAO.BranchDAO;
import com.app.Banking.BankingApplication.DAO.ManagerDAO;
import com.app.Banking.BankingApplication.DTO.Branch;
import com.app.Banking.BankingApplication.DTO.Manager;
import com.app.Banking.BankingApplication.Exception.BranchNotFoundExceotion;
import com.app.Banking.BankingApplication.Exception.ManagerNotFoundException;
@Service
public class BranchService {
	@Autowired
	private BranchDAO dao;
	@Autowired
	private ManagerDAO managerdao;
	
	public ResponseEntity<Branch> saveBranch(Branch branch) {
		return new ResponseEntity<Branch>(dao.saveBranch(branch),HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<Branch> findBranchById(int branchId) {
		Branch dbBranch=dao.findBranchById(branchId);
		if(dbBranch !=null)
			return new ResponseEntity<Branch>(dbBranch,HttpStatus.FOUND);
		else
			throw new BranchNotFoundExceotion("Branch not found for given Id");
	}
	public ResponseEntity<Branch> updateBranch(int oldBranchId,Branch newData) {
		Branch dbBranch = dao.findBranchById(oldBranchId);
		if(dbBranch != null)
			return new ResponseEntity<Branch>(dao.updateBranch(oldBranchId, dbBranch),HttpStatus.ACCEPTED);
		else throw new BranchNotFoundExceotion("Branch not found for given Id");
	}
	public ResponseEntity<List<Branch>> findAllBranch() {
		List<Branch> dblist = dao.findAllBranch();
		if(dblist != null)
			return new ResponseEntity<List<Branch>>(dblist,HttpStatus.FOUND);
		else throw new BranchNotFoundExceotion("There is No Branches in DB");
	}
	public ResponseEntity<Branch> addRelationBranchToManager(int BranchId,int ManagerId ) {
		Branch dbBranch=dao.findBranchById(BranchId);
		Manager dbManager= managerdao.findManagerById(ManagerId);
		if (dbBranch !=null) {
			if (dbManager != null) {
				return new ResponseEntity<Branch>(dao.addRelationBranchToManager(BranchId, ManagerId),HttpStatus.ACCEPTED);
			}else throw new ManagerNotFoundException("Manager not found for given Id");
		}else throw new BranchNotFoundExceotion("Branch not found for given Id");
		
	}
}
