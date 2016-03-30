package com.tikal.fleettracker.monolithic.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goebl.david.Webb;
import com.google.gson.JsonObject;
import com.tikal.fleettracker.monolithic.domain.entity.GPSReading;
import com.tikal.fleettracker.monolithic.domain.entity.Segment;
import com.tikal.fleettracker.monolithic.repository.jpa.SegmentRepository;



@RestController
@Transactional
@RequestMapping("/transits")
public class SegmentService {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SegmentService.class);
	
	@Autowired
	private SegmentRepository segmentRepository;

	public void save(final Segment segment) {
		segmentRepository.save(segment);		
	}

}
