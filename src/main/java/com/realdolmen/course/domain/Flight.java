package com.realdolmen.course.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.realdolmen.course.enums.BudgetClass;

/**
 * These are the flights provided by the companies. The companies will provide
 * this data, including a baseprice (in Price) and VolumeDiscounts. TogethAir
 * sells them with profit.
 * 
 * @author BSEBF08
 *
 */
@Entity
public class Flight implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, length = 15)
	@NotBlank
	@Size(max = 15)
	private String name;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date departureTime;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date arrivalTime;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "availableSeatsPerBudgetClass")
	@MapKeyColumn(name = "budgetClass")
	@Column(name = "available", nullable = false)
	private Map<BudgetClass, Integer> availableSeats = new HashMap<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "pricePerBudgetClass")
	@MapKeyColumn(name = "budgetClass")
	// @Column(name = "prices_id")
	private Map<BudgetClass, Price> prices = new HashMap<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "discountPerVolume")
	@MapKeyColumn(name = "minPeople")
	// @Column(name = "Flight_id")
	private Map<Integer, BigDecimal> volumeDiscounts = new HashMap();

	@ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Company company;

	@ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Airport airportFrom;

	@ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Airport airportTo;

	@Version
	private Integer version;

	// Constructors
	public Flight() {
	}

	public Flight(String name, Date departureTime, Date arrivalTime, Map<BudgetClass, Integer> availableSeats,
			Map<BudgetClass, Price> prices, Map<Integer, BigDecimal> volumeDiscounts, Company company,
			Airport airportFrom, Airport airportTo) {
		this.name = name;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.availableSeats = availableSeats;
		this.prices = prices;
		this.volumeDiscounts = volumeDiscounts;
		this.company = company;
		this.airportFrom = airportFrom;
		this.airportTo = airportTo;
	}

	// Properties

	public Airport getAirportFrom() {
		return airportFrom;
	}

	public void setAirportFrom(Airport airportFrom) {
		this.airportFrom = airportFrom;
	}

	public Airport getAirportTo() {
		return airportTo;
	}

	public void setAirportTo(Airport airportTo) {
		this.airportTo = airportTo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public Map<BudgetClass, Integer> getAvailableSeats() {
		return Collections.unmodifiableMap(availableSeats);
	}

	public Map<BudgetClass, Price> getPrices() {
		return Collections.unmodifiableMap(prices);
	}

	public Integer getVersion() {
		return version;
	}

	/**
	 * public List<VolumeDiscount> getVolumeDiscounts() { return volumeDiscounts; }
	 * 
	 * public void setVolumeDiscounts(List<VolumeDiscount> volumeDiscounts) {
	 * this.volumeDiscounts = volumeDiscounts; }
	 */

	/**
	 * To set the available seats per BudgetClass. If availableSeats is already
	 * present, this method will overwrite this value!
	 * 
	 * @param budgetClass
	 * @param count
	 */
	public void setAvailableSeatsPerBudgetClass(BudgetClass budgetClass, int count) {
		this.availableSeats.put(budgetClass, count);
	}

	public Map<Integer, BigDecimal> getVolumeDiscounts() {
		return volumeDiscounts;
	}

	public void setVolumeDiscounts(Map<Integer, BigDecimal> volumeDiscounts) {
		this.volumeDiscounts = volumeDiscounts;
	}

	/**
	 * To book a number of seats in a certain budgetClass. This is an enumeration of
	 * possible tickets (BudgetClass.ECONOMY, ...)
	 * 
	 * @param budgetClass
	 * @param count
	 */
	public void bookSeats(BudgetClass budgetClass, int count) {
		this.availableSeats.put(budgetClass, availableSeats.get(budgetClass) - count);
	}


	public void revokeSeats(BudgetClass budgetClass, int count) {
		this.availableSeats.put(budgetClass, availableSeats.get(budgetClass) + count);
	}

	/**
	 * To set the price per BudgetClass (cfr. BudgetClass.ECONOMY). If the price is
	 * already present for this budgetclass, this method will overwrite this value!
	 * 
	 * @param budgetClass
	 * @param price
	 */
	public void setPricePerBudgetClass(BudgetClass budgetClass, Price price) {
		this.prices.put(budgetClass, price);
	}
	
	/**
	 * adds this VolumeDiscount to the SortedSet. These are unique elements. By
	 * default, SortedSet does not replace a value by a new element, therefore the
	 * previous value has to be removed first. Afterwards you can add the new value
	 * to the SortedSet
	 * 
	 * @param volumeDiscount
	 * @return
	 */
	public void addVolumeDiscount(Integer minPeople, BigDecimal volumeDiscount) {
		this.volumeDiscounts.put(minPeople, volumeDiscount);
	}
	

}
