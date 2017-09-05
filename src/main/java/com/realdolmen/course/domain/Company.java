package com.realdolmen.course.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author BSEBF08
 * Extern flightCompany, providing flights in our system.
 */
public class Company implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 40)
	@NotBlank @Size(max = 40)
	private String name;
	
	@Column(nullable = false, length = 200)
	@NotBlank @Size(max = 200)
	private String description;
	
	@Version
	private Integer version;
	
	//Constructors
	public Company() {
	}
	public Company(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}


	//Properties
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersion() {
		return version;
	}

	public Long getId() {
		return id;
	}

}