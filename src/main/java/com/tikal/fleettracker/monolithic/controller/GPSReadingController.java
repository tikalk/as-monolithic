package com.tikal.fleettracker.monolithic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tikal.fleettracker.monolithic.service.GPSReadingService;

@RestController
@RequestMapping("/")
public class GPSReadingController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GPSReadingController.class);

	@Autowired
	private GPSReadingService gpsReadingService;

	@RequestMapping(value = "/gps", method = RequestMethod.POST)
	public void saveGPSReading(@RequestBody final String gpsPayload) {
		gpsReadingService.saveGPSReading(gpsPayload);
	}

}
