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
 * 
 * @author JBCBF07
 */

@Stateless
@LocalBean
public class AirportService {

	@EJB
	private AirportRepository airportRepository;

	/**
	 * Get all airports from database
	 * @return
	 */
	public List<Airport> findAll() {
		return airportRepository.findAll();
	}

	/**
	 * Gets all values that can be used by autocomplete
	 * @return
	 */
	public List<String> getPlaceAutoComplete() {
		List<String> auto = new ArrayList<>();
		List<Airport> airportList = findAll();
		for (Airport a : airportList) {
			if (!auto.contains(a.getCity()))
				auto.add(a.getCity());
			if (!auto.contains(a.getCountry()))
				auto.add(a.getCountry());
		}
		return auto;
	}

	/**
	 * Gets all values that can be used by autocomplete
	 * @return
	 */
	public List<String> getCityWithCodeAutoComplete() {
		List<String> auto = new ArrayList<>();
		List<Airport> airportList = findAll();
		for (Airport a : airportList) {
			if (!auto.contains(a.getCity()))
				auto.add(a.getCity() + " (" + a.getCode() + ")");
		}
		return auto;
	}

	/**
	 * Return one airport found by city and code
	 * @param cityAndCode
	 * @return
	 */
	public Airport findAirportByCityWithCode(String cityAndCode) {
		List<Airport> airports = airportRepository.findAirportsByCityWithCode(cityAndCode);
		if (airports != null && airports.size() == 1) {
			return airports.get(0);
		}
		return null;
	}

}
