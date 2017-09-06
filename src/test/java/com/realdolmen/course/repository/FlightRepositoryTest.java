package com.realdolmen.course.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.course.AbstractPersistenceTest;
import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.Company;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Person;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.utils.DateUtils;

public class FlightRepositoryTest extends AbstractPersistenceTest{
	private static final long TEST_FLIGHT_ID = 1;
	private FlightRepository flightRepository;
	
	@Before
	public void initializeRepository() {
		flightRepository = new FlightRepository();
		flightRepository.em = em;
	}
	
	@Test
    public void shouldSaveAFlight() {
		Flight flight = new Flight("AH47", DateUtils.createDate("2016-01-01 00:00"),
        		DateUtils.createDate("2016-01-01 00:00"), new Company("Lufthansa", "Test"));
        System.out.println(flight.getArrivalTime());
        flightRepository.save(flight);
        //em.flush();
		assertNotNull("FlightId is not supposed to be null after saving", flight.getId());
    }
	
	@Test
	public void shouldGetAvailableInEconomyClass() {
		Flight flight = flightRepository.findById(2L);
		Integer availableEconomy = flight.getAvailableSeats().get(BudgetClass.ECONOMY);
		assertEquals((Integer)20, availableEconomy);
	}
	@Test
	public void shouldGetAvailableInBusinessClass() {
		Flight flight = flightRepository.findById(2L);
		flight.bookSeats(BudgetClass.BUSINESS, 10); //originally 30, becomes 20
		flight = flightRepository.save(flight);
		em.flush();
		Flight flight2 = flightRepository.findById(2L);
		Integer availableBusiness = flight2.getAvailableSeats().get(BudgetClass.BUSINESS);
		assertEquals((Integer)20, availableBusiness);
	}
	
	
}
