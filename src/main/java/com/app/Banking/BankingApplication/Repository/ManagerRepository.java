package com.app.Banking.BankingApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Banking.BankingApplication.DTO.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	public  List<Manager> findByName(String managerName);
}
