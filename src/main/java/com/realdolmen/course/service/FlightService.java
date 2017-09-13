package com.realdolmen.course.service;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.repository.FlightRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Comparator;
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

    /**
     * return all flights that match the parameters
     * @param from
     * @param to
     * @param numberOfPassengers
     * @param budgetClass
     * @param departureDate
     * @return
     */
    public List<Flight> searchForAvailableFlights(String from, String to, Integer numberOfPassengers, BudgetClass budgetClass, Date departureDate){
         return flightRepository.searchForAvailableFlights(from, to, numberOfPassengers, budgetClass, departureDate);
    }

    /**
     * return a flight by id
     * @param id
     * @return
     */
    public Flight findById(Long id) {
        return flightRepository.findById(id);
    }

    /**
     * check if a certain amount of seats are available on a flight is a certain budgetClass
     * @param seats
     * @param flight
     * @param budgetClass
     * @return
     */
    public boolean checkIfSeatsAvailable(int seats, Flight flight, BudgetClass budgetClass){
        return flightRepository.checkIfSeatsAvailable(seats, flight, budgetClass);
    }

    /**
     * reserve a certain amount of seats
     * @param size
     * @param bookedFlight
     * @param budgetClass
     */
    public void reserveSeats(int size, Flight bookedFlight, BudgetClass budgetClass) {
        flightRepository.reserveSeats( size,  bookedFlight,  budgetClass);
    }

    /**
     * release a certain amount of seats
     * @param size
     * @param bookedFlight
     * @param budgetClass
     */
    public void revokeSeats(int size, Flight bookedFlight, BudgetClass budgetClass) {
        flightRepository.revokeSeats( size,  bookedFlight,  budgetClass);
    }

    /**
     * Save a flight to the database
     * @param flight
     * @return
     */
    public Flight save(Flight flight) {
    	return flightRepository.save(flight);
    }

}
