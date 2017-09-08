package com.realdolmen.course.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.enums.BudgetClass;
import org.hibernate.cache.spi.QueryResultsRegion;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
public class FlightRepository {
	
	@PersistenceContext
	EntityManager em;	
	
	public Flight save(Flight flight) {
		em.persist(flight.getCompany());
		em.persist(flight.getAirportFrom());
		em.persist(flight.getAirportTo());
		em.persist(flight);
		//em.flush();
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
		// TODO fix cascading manually OR keep flight for archiving
        em.remove(em.getReference(Flight.class, flightId));
    }

	public List<Flight> searchForAvailableFlights(String from, String to, Integer numberOfPassengers, BudgetClass budgetClass, Date departureDate){

//		Date startDepDate = departureDate;
//		Date endDepDate = departureDate;

		Calendar cal = Calendar.getInstance();
		cal.setTime(departureDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDepDate = cal.getTime();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startDepDate = cal.getTime();

		Query q = em.createQuery("select f from Flight f join fetch f.availableSeats s join fetch f.airportFrom af join fetch f.airportTo at" +
				" where (key(s) = :budgetClass AND s >= :numberOfPassengers) " +
				" and (af.country like :from or af.city like :from)" +
				" and (at.country like :to or at.city like :to)" +
				" and f.departureTime between :startDepDate and :endDepDate", Flight.class);

		q.setParameter("budgetClass", budgetClass);
		q.setParameter("numberOfPassengers", numberOfPassengers);
		q.setParameter("from", "%"+from+"%");
		q.setParameter("to", "%"+to+"%");
		q.setParameter("startDepDate", startDepDate);
		q.setParameter("endDepDate", endDepDate);


		return q.getResultList();
	}
	
    

}
