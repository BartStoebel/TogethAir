package com.realdolmen.course.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Testing the Passenger class
 * @author BSEBF08
 *
 */

public class PassengerTest {
	@Test
	public void checkPassengerToStringAndTrimmed() throws Exception {
		Passenger passenger = new Passenger("  Benny    ", "  Slim     ");
		assertEquals("Slim Benny", passenger.toString());
    }	
}
