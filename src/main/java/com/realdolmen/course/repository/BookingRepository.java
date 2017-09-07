package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Booking;

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

    private UserRepository userRepository;

    public BookingRepository() {
    }

    public BookingRepository(UserRepository ur) {
        userRepository = ur;
    }

    public Long save(Booking b){
        userRepository.save(b.getUser());
        em.persist(b);
        return b.getId();
    }

    public Booking findById(Long id){
        return em.find(Booking.class, id);
    }

    public List<Booking> findAll(){
        return em.createQuery("select b from Booking b", Booking.class).getResultList();
    }

}
