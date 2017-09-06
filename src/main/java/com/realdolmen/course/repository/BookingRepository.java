package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Booking;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Repository that will manage all instances of Booking
 * @author JBCBF07
 */

@Stateless
public class BookingRepository {

    @PersistenceContext
    EntityManager em;

    public Booking save(Booking b){
        em.persist(b);
        return b;
    }

    public Booking findById(Long id){
        return em.find(Booking.class, id);
    }

    public List<Booking> findAll(){
        return em.createQuery("select b from Booking b", Booking.class).getResultList();
    }

}
