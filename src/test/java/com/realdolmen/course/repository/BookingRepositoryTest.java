package com.realdolmen.course.repository;

import com.realdolmen.course.AbstractPersistenceTest;
import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Company;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.PaymentChoice;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.PostLoad;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Testing the BookingRepository
 * @author JBCBF07
 */

public class BookingRepositoryTest extends AbstractPersistenceTest {

    private static final Long       TEST_BOOKING_ID = 1L;
    private static final BigDecimal TEST_BOOKING_PRICE = BigDecimal.valueOf(541.20);
    private BookingRepository br;
    private UserRepository ur;

    @Before
    public void init(){
        ur = new UserRepository();
        ur.em = em;
        br = new BookingRepository(ur);
        br.em = em;
    }

    @Test
    public void shouldSaveBookingWithUserWithCompany(){
        Booking booking = new Booking(
                BigDecimal.valueOf(112),
                BigDecimal.valueOf(11.2),
                BigDecimal.valueOf(5.2),
                PaymentChoice.CREDIT_CARD,
                new Date(),
                new User(
                        "Johnny",
                        "De Smedt",
                        "password",
                        "johnny@test3.com",
                        new Address(
                                "Belgium",
                                "boekstraat",
                                "25",
                                "Antwerpen",
                                "2000"
                        ),
                        "+326598875421",
                        DateUtils.createDate("1990-12-12 12:12:12"),
                        Role.CLIENT,
                        new Company(
                                "FlightAirlines",
                                "This is the best airline in the world"
                        )),
                BookingStatus.RESERVED
        );
        Booking test = br.save(booking);
        assertNotNull("Booking id isn't supposed to be null after saving", test.getId());
        //Booking test = br.findById(bookingID);
        assertNotNull(test);
        assertNotNull(test.getUser());
        assertNotNull(test.getUser().getCompany());
    }


    @Test
    public void shouldSaveBookingWithUserWithNoCompany(){
        Booking booking = new Booking(
                BigDecimal.valueOf(112),
                BigDecimal.valueOf(11.2),
                BigDecimal.valueOf(5.2),
                PaymentChoice.CREDIT_CARD,
                new Date(),
                new User(
                        "Johnny",
                        "De Smedt",
                        "password",
                        "johnny@test2.com",
                        new Address(
                                "Belgium",
                                "boekstraat",
                                "25",
                                "Antwerpen",
                                "2000"
                        ),
                        "+326598875421",
                        DateUtils.createDate("1990-12-12 12:12:12"),
                        Role.CLIENT,
                        null),
                BookingStatus.RESERVED
        );
        Booking test = br.save(booking);
        assertNotNull("Booking id isn't supposed to be null after saving", test.getId());
       // Booking test = br.findById(bookingID);
        assertNotNull(test);
        assertNotNull(test.getUser());
        assertNull(test.getUser().getCompany());
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
