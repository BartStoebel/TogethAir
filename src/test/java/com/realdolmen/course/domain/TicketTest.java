package com.realdolmen.course.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Formatter.BigDecimalLayoutForm;

import org.junit.Test;

import com.realdolmen.course.enums.BudgetClass;

/**
 * Testing the Ticket class
 * @author BSEBF08
 *
 */

public class TicketTest {

	@Test
	public void checkTicketWithPassengerToStringAndTrimmed() throws Exception {
		Passenger passenger = new Passenger("  Benny    ", "  Slim     ");
        Ticket ticket = new Ticket(BigDecimal.valueOf(245.67), BudgetClass.ECONOMY, passenger);
		assertEquals("Slim Benny", ticket.getPassenger().toString());
		assertEquals(BigDecimal.valueOf(245.67), ticket.getTicketPrice());
		assertEquals(BudgetClass.ECONOMY, ticket.getBudgetClass());
    }	
	@Test
	public void updateTicket() throws Exception {
		Passenger passenger = new Passenger("  Benny    ", "  Slim     ");
        Ticket ticket = new Ticket(BigDecimal.valueOf(245.67), BudgetClass.ECONOMY, passenger);
		ticket.setTicketPrice(BigDecimal.valueOf(120.3));
		ticket.setBudgetClass(BudgetClass.BUSINESS);
		assertEquals(BigDecimal.valueOf(120.3), ticket.getTicketPrice());
		assertEquals(BudgetClass.BUSINESS, ticket.getBudgetClass());
    }
}
