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
public class VolumeDiscount implements Serializable, Comparable<VolumeDiscount> {
	
	@Column(nullable = false)
	@Min(value = 1)
	private int minPeople;
	
	@Column(nullable = false)
	@Min(value = 0)
	private BigDecimal discountPercentage;

	//Constructor
	public VolumeDiscount() {
	}
	public VolumeDiscount(int people, BigDecimal discountPercentage) {
		super();
		this.minPeople = people;
		this.discountPercentage = discountPercentage;
	}
	
	//Properties
	public int getPeople() {
		return minPeople;
	}
	public void setPeople(int people) {
		this.minPeople = people;
	}
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	//Methods
	/**
	 * 2 VolumeDiscount objects are the same if they have the same value
	 * for minPeople. Equals and hashCode are therefor only based on minPeople.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + minPeople;
		return result;
	}
	/**
	 * 2 VolumeDiscount objects are the same if they have the same value
	 * for minPeople, regardless the value of discountPercentage.
	 * There can only be one discountPercentage for a certain amount of people,
	 * equals() and hashCode() are therefor only based on minPeople.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VolumeDiscount other = (VolumeDiscount) obj;
		if (minPeople != other.minPeople)
			return false;
		return true;
	}
	@Override
    public int compareTo (VolumeDiscount newDiscount) {
    	return Integer.compare(this.minPeople, newDiscount.minPeople);
    }

	
	
	
	
	
	
	

}
