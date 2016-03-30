package com.tikal.fleettracker.monolithic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tikal.fleettracker.monolithic.domain.entity.Guardian;
import com.tikal.fleettracker.monolithic.repository.jpa.GuardianRepository;

@RestController
@Transactional
@RequestMapping("/api/v1")
public class GuardianService {

	@Autowired
	private GuardianRepository guardianRepository;

	// http://localhost:3080/api/v1/guardians/1/vehicles
	@RequestMapping("/guardians")
	public Guardian findGuardianByUsernameWithVehicles(@RequestParam final String username) {
		return guardianRepository.findByUsernameWithVehicles(username);
	}
	
	@RequestMapping("/vehicles/{vehicleId}/guardian/email")
	public String findGuardianEmailByVehicleId(@PathVariable final int vehicleId) {
		return guardianRepository.findEmailByVehicleId(vehicleId);
	}
	


}
