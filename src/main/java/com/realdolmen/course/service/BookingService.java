package com.realdolmen.course.service;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.repository.BookingRepository;
import com.realdolmen.course.repository.FlightRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 * Service that handles logic and repo requests for flights
 * @author JBCBF07
 */

@Stateless @LocalBean
public class BookingService {

    @EJB
    private BookingRepository bookingRepository;

    /**
     * return all bookings of a certain user
     * @param u
     * @return
     */
    public List<Booking> findByUser(User u){
        return bookingRepository.findByUser(u);
    }
}
