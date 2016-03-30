package com.tikal.fleettracker.monolithic.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@GeneratedValue
	private Integer id;

	@NaturalId
	private String serialNumber;

	@NotNull
	private String kind;
	
	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(final String kind) {
		this.kind = kind;
	}

	public Vehicle() {
	}

	public Vehicle(final String serialNumber, final String kind) {
		this.serialNumber = serialNumber;
		this.kind = kind;
	}

	public Integer getId() {
		return id;
	}

}
