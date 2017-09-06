package com.realdolmen.course.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Person;

import javax.persistence.EntityManager;

@Stateless
public class FlightRepository {
	
	@PersistenceContext
	EntityManager em;	
	
	public Flight save(Flight flight) {
		//em.persist(flight.getAvailableSeats().);
		//em.persist(flight.getPrices());
		em.persist(flight.getCompany());
		em.persist(flight);

		em.flush();
		return flight;
	}
	public Flight findById(Long id) {
        return em.find(Flight.class, id);
    }

    public List<Flight> findAll() {
        return em.createQuery("select f from Flight f", Flight.class).getResultList();
    }

    public void remove(long flightId) {
        //logger.info("Removing flight with id " + flightId);
        em.remove(em.getReference(Flight.class, flightId));
    }
	
    

}
