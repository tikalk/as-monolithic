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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "segments")
public class Segment {
	
	public enum Type{place, transit}

	@Id
	@GeneratedValue
	private Integer id;
	
	
	@NotNull
	private boolean isOpen;
	
	@NotNull
	private boolean isNew;

	@NotNull
	private Date startTime;

	@NotNull
	private Date endTime;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	@NotNull
	private Vehicle vehicle;
	
	@NotNull
	private int duration;
	
	@NotNull
	private float lat;
	
	@NotNull
	private float lon;
	
	@NotNull
	private Type type;
	
	
	
	
	private String address;


	public Segment() {
	}

	

	public Integer getId() {
		return id;
	}



	public boolean isOpen() {
		return isOpen;
	}



	public void setOpen(final boolean isOpen) {
		this.isOpen = isOpen;
	}



	public boolean isNew() {
		return isNew;
	}



	public void setNew(final boolean isNew) {
		this.isNew = isNew;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}



	public Vehicle getVehicle() {
		return vehicle;
	}



	public void setVehicle(final Vehicle vehicle) {
		this.vehicle = vehicle;
	}



	public int getDuration() {
		return duration;
	}



	public void setDuration(final int duration) {
		this.duration = duration;
	}



	public float getLat() {
		return lat;
	}



	public void setLat(final float lat) {
		this.lat = lat;
	}



	public float getLon() {
		return lon;
	}



	public void setLon(final float lon) {
		this.lon = lon;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(final String address) {
		this.address = address;
	}



	public void setId(final Integer id) {
		this.id = id;
	}



	public Type getType() {
		return type;
	}



	public void setType(final Type type) {
		this.type = type;
	}
	
	

}
