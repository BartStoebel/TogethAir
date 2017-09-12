package com.realdolmen.course.service;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.repository.FlightRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 * Service that handles logic and repo requests for flights
 * @author JBCBF07
 */

@Stateless @LocalBean
public class FlightService {

    @EJB
    FlightRepository flightRepository;


    public List<Flight> searchForAvailableFlights(String from, String to, Integer numberOfPassengers, BudgetClass budgetClass, Date departureDate){
        return flightRepository.searchForAvailableFlights(from, to, numberOfPassengers, budgetClass, departureDate);
    }

    public Flight findById(Long id) {
        return flightRepository.findById(id);
    }

    public boolean checkIfSeatsAvailable(int seats, Flight flight, BudgetClass budgetClass){
        return flightRepository.checkIfSeatsAvailable(seats, flight, budgetClass);
    }

    public void reserveSeats(int size, Flight bookedFlight, BudgetClass budgetClass) {
        flightRepository.reserveSeats( size,  bookedFlight,  budgetClass);
    }

    public void revokeSeats(int size, Flight bookedFlight, BudgetClass budgetClass) {
        flightRepository.revokeSeats( size,  bookedFlight,  budgetClass);
    }
    
    public Flight save(Flight flight) {
    	return flightRepository.save(flight);
    }

}
