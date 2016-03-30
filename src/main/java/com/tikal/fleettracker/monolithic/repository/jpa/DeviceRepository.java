package com.tikal.fleettracker.monolithic.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tikal.fleettracker.monolithic.domain.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, String> {

	@Query("select d from Device d join fetch d.vehicle where d.imei = ?1")
	Device findDeviceByImei(String imei);

}
