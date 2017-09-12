package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Booking;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @EJB
    private UserRepository userRepository;

    public BookingRepository() {
    }

    public BookingRepository(UserRepository ur) {
        userRepository = ur;
    }

    public Booking save(Booking b){
        //userRepository.save(b.getUser());
        //em.merge(b);
        return em.merge(b);
    }

    public Booking findById(Long id){
        return em.find(Booking.class, id);
    }

    public List<Booking> findAll(){
        return em.createQuery("select b from Booking b", Booking.class).getResultList();
    }

}
