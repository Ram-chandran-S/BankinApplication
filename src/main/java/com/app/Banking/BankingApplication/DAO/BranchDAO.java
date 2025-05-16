package com.app.Banking.BankingApplication.DAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.Banking.BankingApplication.DTO.Branch;
import com.app.Banking.BankingApplication.Repository.BranchRepository;

@Repository
public class BranchDAO {
	@Autowired
	private BranchRepository repo;
	
	
	public Branch saveBranch(Branch branch) {
		return repo.save(generateIfscCode(branch));
	}
	public Branch generateIfscCode(Branch branch) {
		String substr=branch.getBranchName().substring(0,4);
		String Ifsc ;
		 do {
			 String random = String.format("%04d", (int) (Math.random() * 10000));
			  Ifsc =substr+LocalDate.now().getYear()+random;
			  branch.setBranchIFSCcode(Ifsc);
			  return branch;
		} while (repo.existBybranchIFSCcode(Ifsc));
	}
	
	public Branch findBranchById(int branchId) {
		Optional<Branch> dbdata=repo.findById(branchId);
		if (dbdata.isPresent())
			return dbdata.get();
		else return null;
		
	}
	
	public Branch updateBranch(int dbBranchId,Branch newBranchData) {
		Branch dbdata = findBranchById(dbBranchId);
		if(dbdata != null) {
			newBranchData.setBranchId(dbBranchId);
			return repo.save(newBranchData);
		}
		else return null;
	}
	public List<Branch> findAllBranch() {
		List<Branch> brlist= repo.findAll();
		if (brlist != null) 
			return brlist;
		else 
			return null;
	}
}