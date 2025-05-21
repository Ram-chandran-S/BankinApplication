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
import org.springframework.web.bind.annotation.RestController;

import com.app.Banking.BankingApplication.DTO.Manager;
import com.app.Banking.BankingApplication.Service.ManagerService;

@RestController
@RequestMapping("manager")
public class ManagerController {
	@Autowired
	private ManagerService service;
	@PostMapping
	public ResponseEntity<Manager> saveManager(@RequestBody Manager manager) {
		return service.saveManager(manager);
	}
	@GetMapping
	public ResponseEntity<Manager> findManagerById(@RequestParam int managerId) {
		return service.findManagerById(managerId);
	}
	@PutMapping
	public ResponseEntity<Manager> updateManager(@RequestParam int oldId,@RequestBody Manager newData) {
		return service.updateManager(oldId, newData);
	}
	@GetMapping("getAllManagar")
	public ResponseEntity<List<Manager>> findAllManager() {
		return service.findAllManager();
	}
	@GetMapping("getManagerByName")
	public ResponseEntity<List<Manager>> findManagerByName(@RequestParam String managerName) {
		return service.findManagerByName(managerName);
	}

}
