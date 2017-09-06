package com.realdolmen.course.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.utils.DateUtils;

/**
 * Testing the Flight class
 * @author BSEBF08
 *
 */
public class FlightTest {
	@Test
    public void nameEqualsNameOnNewFlight() throws Exception {
        Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
        		DateUtils.createDate("2016-01-01 00:00"), new Company("Lufthansa", "Test"));
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, 16);
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 5);
		assertEquals("AH47", flight.getName());
    }
	
	@Test
    public void setAndBookSeats() throws Exception {
        Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
        		DateUtils.createDate("2016-01-01 00:00"), new Company("Lufthansa", "Test"));
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, 16);
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 3);
		assertEquals((Integer)8, flight.getAvailableSeats().get(BudgetClass.ECONOMY));
    }
	@Test
	public void setProfitPercentagePerBudgetClass() {
		Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
        		DateUtils.createDate("2016-01-01 00:00"), new Company("Lufthansa", "Test"));
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
        		DateUtils.createDate("2016-01-01 00:00"), new Company("Lufthansa", "Test"));
		Price price = new Price();
		price.setBase(BigDecimal.valueOf(230.1));
		price.setFixBonus(BigDecimal.valueOf(2.3));
        flight.setPricePerBudgetClass(BudgetClass.ECONOMY, price);
		//assertEquals((Integer)8, flight.getAvailableSeats().get(BudgetClass.ECONOMY));
		assertTrue(BigDecimal.valueOf(230.1 + 2.3)
				.compareTo(flight.getPrices().get(BudgetClass.ECONOMY).calculatePrice()) == 0);
    }
}