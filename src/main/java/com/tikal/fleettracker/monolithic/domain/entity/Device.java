package com.tikal.fleettracker.monolithic.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "devices")
public class Device {

	@Id
	@GeneratedValue
	private Integer id;

	@NaturalId
	@Column(columnDefinition = "char(15)")
	private String imei;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	

	public Device() {
	}



	public Device(final String imei) {
		this.imei = imei;
	}

	


	public Device(final String imei, final Vehicle vehicle) {
		this.imei = imei;
		this.vehicle = vehicle;
	}



	public Integer getId() {
		return id;
	}



	public String getImei() {
		return imei;
	}

	public void setImei(final String imei) {
		this.imei = imei;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(final Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	

}
