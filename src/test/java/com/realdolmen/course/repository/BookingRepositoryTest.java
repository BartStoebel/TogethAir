package com.realdolmen.course.repository;

import com.realdolmen.course.AbstractPersistenceTest;
import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.PaymentChoice;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing the BookingRepository
 * @author JBCBF07
 */

public class BookingRepositoryTest extends AbstractPersistenceTest {

    private static final Long       TEST_BOOKING_ID = 1L;
    private static final BigDecimal TEST_BOOKING_PRICE = BigDecimal.valueOf(541.20);
    private BookingRepository br;

    @Before
    public void init(){
        br = new BookingRepository();
        br.em = em;
    }

    @Test
    public void shouldSaveBooking(){
        Booking booking = new Booking(
                BigDecimal.valueOf(112),
                BigDecimal.valueOf(11.2),
                BigDecimal.valueOf(5.2),
                PaymentChoice.CREDIT_CARD,
                new Date(),
                new User(),
                BookingStatus.RESERVED
        );
        br.save(booking);
        assertNotNull("Booking id isn't supposed to be null after saving", booking.getId());
    }

    @Test
    public void shouldReturnAllBookings(){
        List<Booking> bookings = br.findAll();
        assertNotNull(bookings);
        assertEquals(4, bookings.size());
    }

    @Test
    public void shouldReturnBookingWithId(){
        Booking booking = br.findById(TEST_BOOKING_ID);
        assertNotNull(booking);
        assertNotNull(booking.getId());
        assertEquals(TEST_BOOKING_PRICE.doubleValue(), booking.getFinalPrice().doubleValue(), 0.001);
    }


}
