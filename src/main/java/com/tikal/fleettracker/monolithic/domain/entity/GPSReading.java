package com.tikal.fleettracker.monolithic.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gps_readings")
public class GPSReading {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date receptionTime;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date readingTime;

	@NotNull
	@Column(columnDefinition = "char(15)")
	private String imei;

	@NotNull
	private float lat;

	@NotNull
	private float lon;

	@NotNull
	private float speed;

	@NotNull
	private float heading;

	@NotNull
	private float distance;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	@NotNull
	private Vehicle vehicle;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "device_id")
	@NotNull
	private Device device;

	public GPSReading() {
	}

	

	public Integer getId() {
		return id;
	}

	public Date getReceptionTime() {
		return receptionTime;
	}

	public Date getReadingTime() {
		return readingTime;
	}

	public String getImei() {
		return imei;
	}

	public float getLat() {
		return lat;
	}

	public float getLon() {
		return lon;
	}

	public float getSpeed() {
		return speed;
	}

	public float getHeading() {
		return heading;
	}

	public float getDistance() {
		return distance;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Device getDevice() {
		return device;
	}
	
	



	public void setId(final Integer id) {
		this.id = id;
	}



	public void setReceptionTime(final Date receptionTime) {
		this.receptionTime = receptionTime;
	}



	public void setReadingTime(final Date readingTime) {
		this.readingTime = readingTime;
	}



	public void setImei(final String imei) {
		this.imei = imei;
	}



	public void setLat(final float lat) {
		this.lat = lat;
	}



	public void setLon(final float lon) {
		this.lon = lon;
	}



	public void setSpeed(final float speed) {
		this.speed = speed;
	}



	public void setHeading(final float heading) {
		this.heading = heading;
	}



	public void setDistance(final float distance) {
		this.distance = distance;
	}



	public void setVehicle(final Vehicle vehicle) {
		this.vehicle = vehicle;
	}



	public void setDevice(final Device device) {
		this.device = device;
	}
	
	

}
