package com.realdolmen.course.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.service.BookingService;
import com.realdolmen.course.service.TicketService;

/**
 * This bean will only exist if the user is logged in. It 
 * contains all the required user information.
 * @author BSEBF08
 *
 */

@Named
@SessionScoped
public class LoggedInBean implements Serializable {
	
	private User user;

	private List<Booking> myPastBookings;
	private List<Ticket> myPastTickets;

	@EJB private BookingService bookingService;
	@EJB private TicketService ticketService;
	
	//Constructors
	public LoggedInBean() {
	}

	public LoggedInBean(User user) {
		super();
		this.user = user;
	}

	//Properties
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Booking> getMyPastBookings() {
		return myPastBookings;
	}

	public void setMyPastBookings(List<Booking> myPastBookings) {
		this.myPastBookings = myPastBookings;
	}

	public List<Ticket> getMyPastTickets() {
		return myPastTickets;
	}

	public void setMyPastTickets(List<Ticket> myPastTickets) {
		this.myPastTickets = myPastTickets;
	}

	//methods
	public String logout() {
		user = null;
		return "index";
	}


	public String goToMyPastBookings(){
		myPastBookings = bookingService.findByUser(user);
		myPastTickets = ticketService.findByUser(user);
		return "pastbookings";
	}




}
