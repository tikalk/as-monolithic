package com.tikal.fleettracker.monolithic.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.goebl.david.Webb;
import com.tikal.fleettracker.monolithic.domain.entity.GPSReading;
import com.tikal.fleettracker.monolithic.domain.entity.Segment;

@Component
public class GPSAnalyzer {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GPSAnalyzer.class);

	
	@Autowired
	private SegmentService segmentService;
	
	private long speedTheshold;
	
	//Should use Redis...
	private final Map<Integer, Segment> currentSegments = new HashMap<>();
	
	private Webb webb;
	
	@Value("${geoCoderUrl}")
	private String geoCoderUrl;
	

	
	
	@PostConstruct
	private void init(){
		webb = Webb.create();
	}

	public void analyzeGPS(final GPSReading gps){
		final int vehicleId = gps.getVehicle().getId();
		logger.info("gps reading time is {}",gps.getReadingTime());
		final Segment.Type gpsSegmentType = gps.getSpeed()>speedTheshold?Segment.Type.transit:Segment.Type.place;
		Segment currentSegment = currentSegments.get(vehicleId);
		if(currentSegment==null){
			currentSegment = buildSegment(gps, gpsSegmentType);
			logger.info("It's the first segment for currentSegments does not contain vehicleId {}. Creating segment with id {} with type {}",vehicleId,currentSegment.getId(),gpsSegmentType);
			currentSegments.put(vehicleId,currentSegment);
			currentSegment.setAddress(getAddress(currentSegment));
			segmentService.save(currentSegment);
		}else{
			if(currentSegment.getType().equals(gpsSegmentType)){
				logger.info("It is still the same segment type {}. Updating Segment with end time {}",gpsSegmentType,gps.getReadingTime());
				//Should be the same segment -> Just update the last GPS and last time
				final Date startTime = currentSegment.getStartTime();
				final Date endTime = gps.getReadingTime();
				currentSegment.setEndTime(endTime);
				currentSegment.setDuration((int)(endTime.getTime()-startTime.getTime()));
				currentSegment.setNew(false);
				currentSegments.put(vehicleId,currentSegment);
				logger.info("Emit an update for existing segment segment for vehicle {}. segment is {}",vehicleId,currentSegment);
				segmentService.save(currentSegment);
			}else{
				logger.info("We have different types. We will close current segment {} with type {} , and new type is {}",currentSegment.getId(),currentSegment.getType(),gpsSegmentType);
				//We will close current segment, and update the lat lon to the last gps, and create a new one
				currentSegment.setOpen(false);
				currentSegment.setNew(false);
				currentSegment.setLat(gps.getLat());
				currentSegment.setLon(gps.getLon());
				//The reading time is the end of current and the start of next segment
				currentSegment.setEndTime(gps.getReadingTime());
				currentSegment.setDuration((int)(currentSegment.getEndTime().getTime()-currentSegment.getStartTime().getTime()));
				logger.info("Closing a segment for vehicle {}. segment is {}",vehicleId,currentSegment);
				segmentService.save(currentSegment);
				
				final Segment newSegment = buildSegment(gps, gpsSegmentType);
				logger.info("Creating new segment type {}. Segment id is {}",gpsSegmentType,newSegment.getId());
				currentSegments.put(vehicleId,newSegment);
				logger.info("Creating a new segment for vehicle {}. segment is {}",vehicleId,newSegment);
				segmentService.save(currentSegment);
			}
		}
	}
	
	private String getAddress(final Segment segment) {
		if(segment.getType().equals(Segment.Type.place) && (segment.isNew() || !segment.isOpen())){
//			webb.get(geoCoderUrl).param("latlng", segment.getLat()+","+segment.getLon()).asString().getBody();
			final JSONObject jsonObject = webb.get(geoCoderUrl).param("latlng", segment.getLat()+","+segment.getLon()).asJsonObject().getBody();
			final JSONArray jsonArray = jsonObject.getJSONArray("results");
			if(jsonArray.length()==0)
				return null;
			final String address = jsonArray.getJSONObject(0).getString("formatted_address");
			return address;
		}
		return null;
	}

	private Segment buildSegment(final GPSReading gps, final Segment.Type gpsSegmentType) {
		final Segment currentSegment = new Segment();
		currentSegment.setOpen(true);
		currentSegment.setNew(true);
		currentSegment.setVehicle(gps.getVehicle());
		currentSegment.setStartTime(gps.getReadingTime());
		currentSegment.setEndTime(gps.getReadingTime());
		currentSegment.setDuration(0);
		currentSegment.setType(gpsSegmentType);
		currentSegment.setLat(gps.getLat());
		currentSegment.setLon(gps.getLon());
		return currentSegment;
	}

	
}
