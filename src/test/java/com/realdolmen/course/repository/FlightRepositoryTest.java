package com.realdolmen.course.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.course.AbstractPersistenceTest;
import com.realdolmen.course.domain.Company;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Price;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.utils.DateUtils;

public class FlightRepositoryTest extends AbstractPersistenceTest{
    private static final long TEST_FLIGHT_ID = 2L;

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
		Price price = new Price();
        price.setBase(BigDecimal.valueOf(126.32));
        price.setProfitPercentage(BigDecimal.valueOf(-5));
        flight.setPricePerBudgetClass(BudgetClass.ECONOMY, price);
        price = new Price();
        price.setBase(BigDecimal.valueOf(126.32));
        price.setFixBonus(BigDecimal.valueOf(55));
        flight.setPricePerBudgetClass(BudgetClass.BUSINESS, price);
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, 25);
        flight.setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, 5);
		flight = flightRepository.save(flight);
       
		assertNotNull("FlightId is not supposed to be null after saving", flight.getId());
		//assertNotNull()
    }	
	@Test
	public void shouldReturnAllFlights() {
		List<Flight> flights = flightRepository.findAll();
		assertNotNull(flights);
		assertEquals(3, flights.size());
	}
	@Test
	public void shouldReturnAFlight() {
		Flight flight = flightRepository.findById(TEST_FLIGHT_ID);
		assertNotNull(flight);
		assertNotNull(flight.getId());
		assertEquals("AB17", flight.getName());
	}
	@Test
	public void shouldRemoveAFlight() {
		flightRepository.remove(TEST_FLIGHT_ID);
		assertEquals(2, count(Flight.class));
	}
	@Test
	public void shouldGetAvailableSeatsInEconomyClass() {
		Flight flight = flightRepository.findById(2L);
		Integer availableEconomy = flight.getAvailableSeats().get(BudgetClass.ECONOMY);
		assertEquals((Integer)20, availableEconomy);
	}
	@Test
	public void shouldGetAvailableSeatsInBusinessClass() {
		Flight flight = flightRepository.findById(2L);
		flight.bookSeats(BudgetClass.BUSINESS, 10); //originally 30, becomes 20
		flight = flightRepository.save(flight);
		em.flush();
		Flight flight2 = flightRepository.findById(2L);
		Integer availableBusiness = flight2.getAvailableSeats().get(BudgetClass.BUSINESS);
		assertEquals((Integer)20, availableBusiness);
	}
	
	
	
}
