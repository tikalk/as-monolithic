package com.tikal.fleettracker.monolithic.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tikal.fleettracker.monolithic.domain.entity.Segment;

public interface SegmentRepository extends JpaRepository<Segment, Integer> {

}
