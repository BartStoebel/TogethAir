package com.realdolmen.course.repository;

import com.realdolmen.course.AbstractPersistenceTest;
import com.realdolmen.course.domain.*;
import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.enums.PaymentChoice;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Testing the Ticket Repository's actions.
 * @author JBCBF07
 */

public class TicketRepositoryTest extends AbstractPersistenceTest {

    private static final Long       TEST_TICKET_ID = 3L;

    private TicketRepository tr;
    private BookingRepository br;
    private FlightRepository fr;
    private UserRepository ur;


    @Before
    public void init(){
        ur = new UserRepository();
        ur.em = em;
        br = new BookingRepository(ur);
        br.em = em;
        fr = new FlightRepository();
        fr.em = em;
        tr = new TicketRepository(br, fr);
        tr.em = em;
    }

    @Test
    public void shouldSaveTicketWithUserWithCompany(){
        Map<BudgetClass, Price> budgetClassPriceMap = new HashMap<>();
        budgetClassPriceMap.put(BudgetClass.BUSINESS, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));
        budgetClassPriceMap.put(BudgetClass.FIRST_CLASS, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));
        budgetClassPriceMap.put(BudgetClass.ECONOMY, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));

        Map<BudgetClass, Integer> budgetClassIntegerMap = new HashMap<>();
        budgetClassIntegerMap.put(BudgetClass.BUSINESS, 25);
        budgetClassIntegerMap.put(BudgetClass.FIRST_CLASS, 25);
        budgetClassIntegerMap.put(BudgetClass.ECONOMY, 25);

        Map<Integer, BigDecimal> volumeDiscounts = new HashMap();
        volumeDiscounts.put(2, BigDecimal.valueOf(2));
        volumeDiscounts.put(5, BigDecimal.valueOf(5));

        Ticket ticket = new Ticket(
                BigDecimal.valueOf(120.0),
                BudgetClass.BUSINESS,
                new Passenger(
                        "Joris",
                        "Boschmans"
                ),
                new Booking(
                        BigDecimal.valueOf(112),
                        BigDecimal.valueOf(11.2),
                        BigDecimal.valueOf(5.2),
                        PaymentChoice.CREDIT_CARD,
                        new Date(),
                        new User(
                                "Johnny",
                                "De Smedt",
                                "password",
                                "johnny@test.com",
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
                ),
                new Flight(
                        "AH47",
                        DateUtils.createDate("2016-01-01 00:00"),
                        DateUtils.createDate("2016-01-01 00:00"),
                        budgetClassIntegerMap,
                        budgetClassPriceMap,
                        volumeDiscounts,
                        new Company(
                                "Lufthansa",
                                "Test"
                        ),
                        new Airport(
                                "Brussels Airport",
                                "Belgium",
                                "Europe",
                                "ZAV",
                                "Zaventem"
                        ),
                        new Airport(
                                "Brussels Airport",
                                "Belgium",
                                "Europe",
                                "ZAV",
                                "Zaventem"
                        )
                )
        );
        Ticket test = tr.save(ticket);
        assertNotNull("Ticket ID should not still be null after being saved", test.getId());
        assertNotNull(test);
        assertNotNull(test.getPassenger());
        assertNotNull(test.getBooking());
        assertNotNull(test.getBooking().getUser());
        assertNotNull(test.getBooking().getUser().getCompany());
        assertNotNull(test.getFlight());
        assertNotNull(test.getFlight().getCompany());
    }


    @Test
    public void shouldSaveTicketWithUserWithoutCompany(){
        Map<BudgetClass, Price> budgetClassPriceMap = new HashMap<>();
        budgetClassPriceMap.put(BudgetClass.BUSINESS, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));
        budgetClassPriceMap.put(BudgetClass.FIRST_CLASS, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));
        budgetClassPriceMap.put(BudgetClass.ECONOMY, new Price(BigDecimal.valueOf(120), BigDecimal.TEN, null));

        Map<BudgetClass, Integer> budgetClassIntegerMap = new HashMap<>();
        budgetClassIntegerMap.put(BudgetClass.BUSINESS, 25);
        budgetClassIntegerMap.put(BudgetClass.FIRST_CLASS, 25);
        budgetClassIntegerMap.put(BudgetClass.ECONOMY, 25);

        Map<Integer, BigDecimal> volumeDiscounts = new HashMap();
        volumeDiscounts.put(2, BigDecimal.valueOf(2));
        volumeDiscounts.put(5, BigDecimal.valueOf(5));
        
        Ticket ticket = new Ticket(
                BigDecimal.valueOf(120.0),
                BudgetClass.BUSINESS,
                new Passenger(
                        "Joris",
                        "Boschmans"
                ),
                new Booking(
                        BigDecimal.valueOf(112),
                        BigDecimal.valueOf(11.2),
                        BigDecimal.valueOf(5.2),
                        PaymentChoice.CREDIT_CARD,
                        new Date(),
                        new User(
                                "Johnny",
                                "De Smedt",
                                "password",
                                "johnny@test.com",
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
                                null
                        ),
                        BookingStatus.RESERVED
                ),
                new Flight(
                        "AH47",
                        DateUtils.createDate("2016-01-01 00:00"),
                        DateUtils.createDate("2016-01-01 00:00"),
                        budgetClassIntegerMap,
                        budgetClassPriceMap,
                        volumeDiscounts,
                        new Company(
                                "Lufthansa",
                                "Test"
                        ),
                        new Airport(
                                "Brussels Airport",
                                "Belgium",
                                "Europe",
                                "ZAV",
                                "Zaventem"
                        ),
                        new Airport(
                                "Brussels Airport",
                                "Belgium",
                                "Europe",
                                "ZAV",
                                "Zaventem"
                        )
                )
        );
        Ticket test = tr.save(ticket);
        assertNotNull("Ticket ID should not still be null after being saved", test.getId());
        assertNotNull(test);
        assertNotNull(test.getPassenger());
        assertNotNull(test.getBooking());
        assertNotNull(test.getBooking().getUser());
        assertNull(test.getBooking().getUser().getCompany());
        assertNotNull(test.getFlight());
        assertNotNull(test.getFlight().getCompany());
    }

    @Test
    public void shouldReturnTicketWithId(){
        Ticket t = tr.findById(TEST_TICKET_ID);
        assertNotNull(t);
        assertNotNull(t.getId());
        assertNotNull(t.getPassenger());
        assertNotNull(t.getFlight());
        assertNotNull(t.getFlight().getCompany());
        assertNotNull(t.getBooking());
        assertNotNull(t.getBooking().getUser());
        assertNotNull(t.getBooking().getUser().getCompany());
        assertEquals("Air France", t.getBooking().getUser().getCompany().getName());
        assertEquals("BB17", t.getFlight().getName());
    }

    @Test
    public void shouldReturnAllTickets(){
        List<Ticket> tickets = tr.findAll();
        assertNotNull(tickets);
        assertEquals(3, tickets.size());
    }



}
