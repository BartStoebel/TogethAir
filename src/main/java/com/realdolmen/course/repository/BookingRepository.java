package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    /**
     * Save a booking to the database
     * @param b
     * @return
     */
    public Booking save(Booking b){
        return em.merge(b);
    }

    /**
     * Find all bookings of a user
     * @param ub
     * @return
     */
    public List<Booking> findByUser(User u){
        Query q = em.createQuery("select b from Booking b join fetch b.user u where u = :u", Booking.class);
        q.setParameter("u", u);
        return q.getResultList();
    }

    /**
     * Find a booking using id
     * @param id
     * @return
     */
    public Booking findById(Long id){
        return em.find(Booking.class, id);
    }

    /**
     * Return all bokkings in the database
     * @return
     */
    public List<Booking> findAll(){
        return em.createQuery("select b from Booking b", Booking.class).getResultList();
    }
}
