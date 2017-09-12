package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Ticket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import java.util.List;

/**
 * Repository of the Ticket class
 * @author JBCBF07
 */

@Stateless
public class TicketRepository {

    @PersistenceContext
    EntityManager em;

    @EJB
    private BookingRepository bookingRepository;
    @EJB
    private FlightRepository flightRepository;

    public TicketRepository() {
    }

    public TicketRepository(BookingRepository bookingRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
    }

    /*@PostLoad
    public void init(){
        bookingRepository = new BookingRepository();
        flightRepository = new FlightRepository();
    }*/

    public Long save(Ticket ticket){
        bookingRepository.save(ticket.getBooking());
        flightRepository.save(ticket.getFlight());
        em.merge(ticket);
        em.flush();
        return ticket.getId();
    }

    public void save(List<Ticket> tickets){
        for (Ticket ticket : tickets){
            bookingRepository.save(ticket.getBooking());
            flightRepository.save(ticket.getFlight());
            em.merge(ticket);
        }

        em.flush();
    }

    public Ticket findById(Long id){
        return em.find(Ticket.class, id);
    }

    public List<Ticket> findAll(){
        return em.createQuery("select t from Ticket t", Ticket.class).getResultList();
    }




}
