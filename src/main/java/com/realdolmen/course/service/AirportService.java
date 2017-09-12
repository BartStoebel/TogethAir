package com.realdolmen.course.service;

import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.repository.AirportRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Service that handles logic and repo requests for airports
 * @author JBCBF07
 */

@Stateless @LocalBean
public class AirportService {

    @EJB
    private AirportRepository airportRepository;

    public List<Airport> findAll(){
        return airportRepository.findAll();
    }

    public List<String> getPlaceAutoComplete(){
        List<String> auto = new ArrayList<>();
        List<Airport> airportList = findAll();
        for (Airport a : airportList){
            if (!auto.contains(a.getCity())) auto.add(a.getCity());
            if (!auto.contains(a.getCountry())) auto.add(a.getCountry());
        }
        return auto;
    }


}
