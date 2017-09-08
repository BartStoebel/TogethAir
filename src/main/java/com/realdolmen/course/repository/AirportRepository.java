package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Airport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository that will manage all instances of Airport
 * @author JBCBF07
 */
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

}
