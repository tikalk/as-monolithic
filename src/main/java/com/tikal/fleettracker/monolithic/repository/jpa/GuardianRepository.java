package com.tikal.fleettracker.monolithic.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tikal.fleettracker.monolithic.domain.entity.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
	public Guardian findByUsername(String username);

	@Query("select g from Guardian g left join fetch g.vehicles where g.username = ?1")
	public Guardian findByUsernameWithVehicles(String guardianName);

	@Query("select g.email from Guardian g join g.vehicles a where a.id = ?1")
	public String findEmailByVehicleId(int vehicleId);
}
