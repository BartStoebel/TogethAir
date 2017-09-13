package com.realdolmen.course.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.Min;

import com.realdolmen.course.enums.BudgetClass;

/**
 * 1 Ticket will contain all the information needed for 1 passenger per flight.
 * @author BSEBF08
 *
 */

@Entity
public class Ticket implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false)
	@Min(value = 0)
	private BigDecimal ticketPrice;
	
	@Column(nullable = false)
	@Enumerated (EnumType.STRING) 
	private BudgetClass budgetClass;
	
	@Embedded
	private Passenger passenger;

	@ManyToOne (/*cascade = CascadeType.MERGE, */fetch = FetchType.EAGER)
	private Flight flight;
	
	@Version 
	private Integer version;
	
	//Constructors
	public Ticket() {
	}

	public Ticket(BigDecimal ticketPrice, BudgetClass budgetClass, Passenger passenger, Flight flight) {
		this.ticketPrice = ticketPrice;
		this.budgetClass = budgetClass;
		this.passenger = passenger;
		this.flight = flight;
	}

	//Properties
	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public BudgetClass getBudgetClass() {
		return budgetClass;
	}

	public void setBudgetClass(BudgetClass budgetClass) {
		this.budgetClass = budgetClass;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Long getId() {
		return Id;
	}

	public Integer getVersion() {
		return version;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
