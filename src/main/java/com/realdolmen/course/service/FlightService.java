package com.realdolmen.course.service;

import com.realdolmen.course.domain.Flight;
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

}
