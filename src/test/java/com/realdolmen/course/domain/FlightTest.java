package com.realdolmen.course.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.utils.DateUtils;

/**
 * Testing the Flight class
 * @author BSEBF08
 *
 */

public class FlightTest {

	private Map<BudgetClass, Price> budgetClassPriceMap;
	private Map<BudgetClass, Integer> budgetClassIntegerMap;
	private List<VolumeDiscount> volumeDiscounts;

	@Before
	public void initClass(){
		budgetClassPriceMap = new HashMap<>();
		budgetClassPriceMap.put(BudgetClass.BUSINESS, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));
		budgetClassPriceMap.put(BudgetClass.FIRST_CLASS, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));
		budgetClassPriceMap.put(BudgetClass.ECONOMY, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));

		budgetClassIntegerMap = new HashMap<>();
		budgetClassIntegerMap.put(BudgetClass.BUSINESS, 25);
		budgetClassIntegerMap.put(BudgetClass.FIRST_CLASS, 25);
		budgetClassIntegerMap.put(BudgetClass.ECONOMY, 25);

		volumeDiscounts = new ArrayList<>();
//		volumeDiscounts.add(new VolumeDiscount(5, BigDecimal.valueOf(5)));
//		volumeDiscounts.add(new VolumeDiscount(10, BigDecimal.valueOf(10)));
	}

	@Test
    public void nameEqualsNameOnNewFlight() throws Exception {
		Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
				DateUtils.createDate("2016-01-01 00:00"), budgetClassIntegerMap, budgetClassPriceMap, volumeDiscounts,
				new Company("Lufthansa", "Test"),
				new Airport(), new Airport());
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, 16);
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 5);
		assertEquals("AH47", flight.getName());
    }
	@Test
    public void setAndBookSeats() throws Exception {
		Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
				DateUtils.createDate("2016-01-01 00:00"), budgetClassIntegerMap, budgetClassPriceMap, volumeDiscounts,
				new Company("Lufthansa", "Test"),
				new Airport(), new Airport());
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, 16);
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 3);
		assertEquals((Integer)8, flight.getAvailableSeats().get(BudgetClass.ECONOMY));
    }
	@Test
	public void setProfitPercentagePerBudgetClass() {
		Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
				DateUtils.createDate("2016-01-01 00:00"), budgetClassIntegerMap, budgetClassPriceMap, volumeDiscounts,
				new Company("Lufthansa", "Test"),
				new Airport(), new Airport());
		Price price = new Price();
		price.setBase(BigDecimal.valueOf(230.1));
		price.setProfitPercentage(BigDecimal.valueOf(6));
        flight.setPricePerBudgetClass(BudgetClass.ECONOMY, price);
		//assertEquals((Integer)8, flight.getAvailableSeats().get(BudgetClass.ECONOMY));
		assertTrue(BigDecimal.valueOf(230.1 * 1.06)
				.compareTo(flight.getPrices().get(BudgetClass.ECONOMY).calculatePrice()) == 0);
    }	
	@Test
	public void setfixBonusPerBudgetClass() {
		Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
				DateUtils.createDate("2016-01-01 00:00"), budgetClassIntegerMap, budgetClassPriceMap, volumeDiscounts,
				new Company("Lufthansa", "Test"),
				new Airport(), new Airport());
		Price price = new Price();
		price.setBase(BigDecimal.valueOf(230.1));
		price.setFixBonus(BigDecimal.valueOf(2.3));
        flight.setPricePerBudgetClass(BudgetClass.ECONOMY, price);
		//assertEquals((Integer)8, flight.getAvailableSeats().get(BudgetClass.ECONOMY));
		assertTrue(BigDecimal.valueOf(230.1 + 2.3)
				.compareTo(flight.getPrices().get(BudgetClass.ECONOMY).calculatePrice()) == 0);
    }
	@Test
	public void setAndChangeVolumeDiscounts() {
		Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
				DateUtils.createDate("2016-01-01 00:00"), budgetClassIntegerMap, budgetClassPriceMap, volumeDiscounts,
				new Company("Lufthansa", "Test"),
				new Airport(), new Airport());
		flight.addVolumeDiscount(new VolumeDiscount(5, BigDecimal.valueOf(5)));
		flight.addVolumeDiscount(new VolumeDiscount(10, BigDecimal.valueOf(7.5)));
		flight.addVolumeDiscount(new VolumeDiscount(15, BigDecimal.valueOf(10)));
		flight.addVolumeDiscount(new VolumeDiscount(5, BigDecimal.valueOf(27.1)));
		assertEquals(3, flight.getVolumeDiscounts().size());
		//check last value in list: 
		assertEquals(BigDecimal.valueOf(27.1), flight.getVolumeDiscounts().get(2).getDiscountPercentage());
		//check discount in list where minPeople = 15
		Optional<VolumeDiscount> volumeDiscount = flight.getVolumeDiscounts()
				.stream()
				.filter(v -> v.getPeople() == 15)
				.findFirst();
		if(volumeDiscount.isPresent()) {
			VolumeDiscount v = volumeDiscount.get() ;
			assertEquals(BigDecimal.valueOf(10), v.getDiscountPercentage());
		}
	}
	
}




