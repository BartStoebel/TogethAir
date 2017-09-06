package com.realdolmen.course.domain;

import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.PaymentChoice;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

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
        booking = new Booking(
                TEST_PRICE,
                TEST_DISCOUNT_VOLUME,
                TEST_DISCOUNT_CC,
                PaymentChoice.CREDIT_CARD,
                new Date(),
                new User(),
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
