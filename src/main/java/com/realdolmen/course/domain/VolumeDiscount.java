package com.realdolmen.course.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

/**
 * This class contains all the info needed for discounts based on an amount of sold tickets on one flight.
 * This values are provided by the airline companies.
 * @author BSEBF08
 *
 */
@Embeddable
public class VolumeDiscount implements Serializable {
	
	@Column(nullable = false)
	@Min(value = 1)
	private int people;
	
	@Column(nullable = false)
	@Min(value = 0)
	private BigDecimal discountPercentage;

	//Constructor
	public VolumeDiscount() {
	}
	public VolumeDiscount(int people, BigDecimal discountPercentage) {
		super();
		this.people = people;
		this.discountPercentage = discountPercentage;
	}
	
	//Properties
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	
	
	
	

}
