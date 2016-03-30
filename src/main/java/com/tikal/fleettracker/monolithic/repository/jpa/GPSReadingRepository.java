package com.tikal.fleettracker.monolithic.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tikal.fleettracker.monolithic.domain.entity.GPSReading;

public interface GPSReadingRepository extends JpaRepository<GPSReading, Integer> {


}
