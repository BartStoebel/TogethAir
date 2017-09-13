package com.realdolmen.course.domain;

import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.enums.PaymentChoice;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testing the booking class.
 * @author JBCBF07
 */
public class BookingTest {

    private final BigDecimal TEST_PRICE = BigDecimal.valueOf(120.25);
    private final BigDecimal TEST_DISCOUNT_VOLUME = null;
    private final BigDecimal TEST_DISCOUNT_CC = BigDecimal.valueOf(12.54);
    private Booking booking;

    @Before
    public void init(){

        Map<BudgetClass, Integer> availableSeats = new HashMap<>();
        availableSeats.put(BudgetClass.ECONOMY, 12);
        availableSeats.put(BudgetClass.BUSINESS, 12);
        Map<BudgetClass, Price> prices = new HashMap<>();
        prices.put(BudgetClass.ECONOMY, new Price(
                BigDecimal.TEN,
                BigDecimal.ONE,
                null
        ));
        prices.put(BudgetClass.BUSINESS, new Price(
                BigDecimal.TEN,
                null,
                BigDecimal.ONE
        ));
        Map<Integer, BigDecimal> volDiscounts = new HashMap<>();
        volDiscounts.put(2, BigDecimal.ONE);
        volDiscounts.put(8, BigDecimal.TEN);

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket(
                BigDecimal.TEN,
                BudgetClass.BUSINESS,
                new Passenger(
                        "Joris",
                        "Boschmans"
                ),
                new Flight(
                        "New Flight",
                        new Date(),
                        new Date(),
                        availableSeats,
                        prices,
                        volDiscounts,
                        new Company(
                                "new Company",
                                "Best company ever"
                        ),
                        new Airport(
                                "Dubai Central",
                                "UAE",
                                "Asia",
                                "DAI",
                                "Dubai"
                        ),
                        new Airport(
                                "Dubai Central",
                                "UAE",
                                "Asia",
                                "DAI",
                                "Dubai"
                        )
                )
        ));

        booking = new Booking(
                TEST_PRICE,
                TEST_DISCOUNT_VOLUME,
                TEST_DISCOUNT_CC,
                PaymentChoice.CREDIT_CARD,
                new Date(),
                new User(),
                ticketList,
                BookingStatus.RESERVED
        );
    }

    @Test
    public void shouldHaveNullAsIdWhenNotSaved(){
        assertNull("Booking should have null id when not yet saved", booking.getId());
    }

    @Test
    public void shouldReturnTheCorrectPriceWithoutDiscount(){
        assertEquals(TEST_PRICE.add(TEST_DISCOUNT_CC), booking.calcFinalPriceWithoutDiscounts());
    }

}
