package com.realdolmen.course.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PassengerInfo is needed for booking a ticket per flight. We will only keep
 * firstName and lastName ...
 * @author BSEBF08
 *
 */

@Embeddable
public class Passenger implements Serializable{

	@Column(nullable = false)
	@NotBlank @Size(max = 40)
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank @Size(max = 40)
	private String lastName;
	
	
	//Constructors
	public Passenger() {
	}

	public Passenger(String firstName, String lastName) {
		super();
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	//Methods
	@Override
	public String toString() {
		return lastName + " " + firstName;
	}
	
}
