package com.app.Banking.BankingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Banking.BankingApplication.DTO.Branch;
import com.app.Banking.BankingApplication.Service.BranchService;


@RestController
@RequestMapping("Branch")
public class BranchController {
	@Autowired
	private BranchService service;
	@PostMapping
	public ResponseEntity<Branch> saveBranch(@RequestBody Branch branch) {
		return service.saveBranch(branch);
	}
	@GetMapping
	public ResponseEntity<Branch> findBranchById(@RequestParam int branchId) {
		return service.findBranchById(branchId);
	}
	@PutMapping
	public ResponseEntity<Branch> updateBranch(@RequestParam int oldId,@RequestBody Branch newData) {
		return service.updateBranch(oldId, newData);
	}
	@GetMapping("getAllBranch")
	public ResponseEntity<List<Branch>> findAllBranch() {
		return service.findAllBranch();
	}
	@PutMapping("addRelationBranhToManager")
	public ResponseEntity<Branch> addRelationBranhToManager(int BranchId,int ManagerId ) {
		return service.addRelationBranchToManager(BranchId, ManagerId);
	}

}
