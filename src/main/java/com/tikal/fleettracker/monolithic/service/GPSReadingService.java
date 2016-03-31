package com.tikal.fleettracker.monolithic.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tikal.fleettracker.monolithic.domain.entity.Device;
import com.tikal.fleettracker.monolithic.domain.entity.GPSReading;
import com.tikal.fleettracker.monolithic.repository.jpa.DeviceRepository;
import com.tikal.fleettracker.monolithic.repository.jpa.GPSReadingRepository;

@Service
@Transactional
public class GPSReadingService {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GPSReadingService.class);

	
	@Autowired
	private GPSReadingRepository gpsReadingRepository;
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private GPSAnalyzer gpsAnalyzer;
	
	private final SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
	
	

	public void saveGPSReading(@RequestBody final String gpsPayload){
		final GPSReading gpsReading = parse(gpsPayload);
		final Device device = deviceRepository.findDeviceByImei(gpsReading.getImei());
		if(device==null){
			logger.warn("Unknown imei in GPSReading imei is: {}",gpsReading.getImei());
		}else{
			gpsReading.setDevice(device);
			gpsReading.setVehicle(device.getVehicle());
			gpsReadingRepository.save(gpsReading);
			gpsAnalyzer.analyzeGPS(gpsReading);
		}
	}



	private GPSReading parse(final String gpsPayload) {		
		try {
			final String[] csvValues = gpsPayload.split(",");
			final GPSReading gps = new GPSReading();
			gps.setReceptionTime(new Date());
			gps.setImei(csvValues[1]);
			gps.setLat(Float.valueOf(csvValues[4]));
			gps.setLon(Float.valueOf(csvValues[5]));
			gps.setReadingTime(df.parse((csvValues[6])));
			gps.setSpeed(Float.valueOf(csvValues[10]));
			gps.setHeading(Float.valueOf(csvValues[11]));
			gps.setDistance(Float.valueOf(csvValues[14]));
			
			return gps;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
