package com.realdolmen.course.beans;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.service.BookingService;

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

	@EJB private BookingService bookingService;
	
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

	//methods
	public String logout() {
		user = null;
		return "index";
	}


	public String goToMyPastBookings(){
		myPastBookings = bookingService.findByUser(user);
		myPastBookings.sort(new Comparator<Booking>() {
			@Override
			public int compare(Booking o1, Booking o2) {
				Date date1 = o1.getTickets().get(0).getFlight().getDepartureTime();
				Date date2 = o2.getTickets().get(0).getFlight().getDepartureTime();

				return date1.compareTo(date2);
			}
		});
		return "pastbookings";
	}




}
