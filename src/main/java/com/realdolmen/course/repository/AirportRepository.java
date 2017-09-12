package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Airport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository that will manage all instances of Airport
 * @author JBCBF07
 */

@Stateless
public class AirportRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Airport a){
        em.persist(a);
        return a.getId();
    }

    public Airport findById(Long id){
        return em.find(Airport.class, id);
    }

    public List<Airport> findAll(){
        return em.createQuery("select a from Airport a order by a.region, a.country, a.city, a.name", Airport.class).getResultList();
    }

    public List<Airport> findAllWithCityOrCountryLike(String param){
        Query q = em.createQuery("select a from Airport a where a.city LIKE :param OR a.country LIKE :param order by a.region, a.country, a.city, a.name", Airport.class);
        q.setParameter("param", "%" + param + "%");
        return q.getResultList();
    }
    public List<Airport> findAirportsByCityWithCode(String cityAndCode) {
    	if (cityAndCode.contains("(")) {
    		String[] parts = cityAndCode.split("\\(");
        	String city = parts[0]; // city
        	System.out.println(city);
        	String code = parts[1]; // code
        	code = code.substring(0, code.length() - 1);
        	System.out.println(code);
        	
        	Query q = em.createQuery("select a from Airport a where a.city = :param AND a.code = :code", Airport.class);
            q.setParameter("param", city);
            q.setParameter("code", code);
        	return q.getResultList();
    	}
    	return null;
    }

}
