package com.app.Banking.BankingApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Banking.BankingApplication.DTO.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
		boolean existBybranchIFSCcode(String IFSCcode);
}
