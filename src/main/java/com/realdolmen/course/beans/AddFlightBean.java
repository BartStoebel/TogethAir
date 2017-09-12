package com.realdolmen.course.beans;


import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.realdolmen.course.domain.Flight;

@Named
@RequestScoped
public class AddFlightBean {
	
	private Flight flight = new Flight();
	
	@NotBlank
	private String from;
	@NotBlank
	private String to;

	@Min(0)
	private Integer availableFirstClass = 0;
	
	@Min(0)
	private Integer availableBusiness = 0;
	@Min(0)
	private Integer availableEconomy = 0;
	
	private BigDecimal priceFirstClass = BigDecimal.ZERO;
	private BigDecimal priceBusiness = BigDecimal.ZERO;
	private BigDecimal priceEconomy = BigDecimal.ZERO;
	
	//Construcor
	public AddFlightBean() {
		priceFirstClass.setScale(2,RoundingMode.HALF_UP);
		priceBusiness.setScale(2,RoundingMode.HALF_UP);
		priceEconomy.setScale(2,RoundingMode.HALF_UP);
	}
	
	

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Integer getAvailableFirstClass() {
		return availableFirstClass;
	}

	public void setAvailableFirstClass(Integer availableFirstClass) {
		this.availableFirstClass = availableFirstClass;
	}

	public Integer getAvailableBusiness() {
		return availableBusiness;
	}

	public void setAvailableBusiness(Integer availableBusiness) {
		this.availableBusiness = availableBusiness;
	}

	public Integer getAvailableEconomy() {
		return availableEconomy;
	}

	public void setAvailableEconomy(Integer availableEconomy) {
		this.availableEconomy = availableEconomy;
	}

	public BigDecimal getPriceFirstClass() {
		return priceFirstClass;
	}

	public void setPriceFirstClass(BigDecimal priceFirstClass) {
		this.priceFirstClass = priceFirstClass;
	}

	public BigDecimal getPriceBusiness() {
		return priceBusiness;
	}

	public void setPriceBusiness(BigDecimal priceBusiness) {
		this.priceBusiness = priceBusiness;
	}

	public BigDecimal getPriceEconomy() {
		return priceEconomy;
	}

	public void setPriceEconomy(BigDecimal priceEconomy) {
		this.priceEconomy = priceEconomy;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	//Methods
	public String save() {
		//TODO: company opslaan uit user
		System.out.println(priceFirstClass);
		System.out.println(flight.getArrivalTime());
		return "";
	}
}
