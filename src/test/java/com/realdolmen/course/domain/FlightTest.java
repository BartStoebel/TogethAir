package com.realdolmen.course.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.utils.DateUtils;

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
    public void manipulateSeats() throws Exception {
        Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
        		DateUtils.createDate("2016-01-01 00:00"), new Company("Lufthansa", "Test"));
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, 16);
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 5);
        flight.bookSeats(BudgetClass.ECONOMY, 3);
		assertEquals((Integer)8, flight.getAvailableSeats().get(BudgetClass.ECONOMY));
    }

	
}
