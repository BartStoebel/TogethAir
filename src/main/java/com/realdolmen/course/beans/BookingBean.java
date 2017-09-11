package com.realdolmen.course.beans;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import org.hibernate.Session;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.List;

/**
 * This bean will hold the information about a booking.
 * @author JBCBF07
 */

@Named @SessionScoped
public class BookingBean implements Serializable {

    private Flight bookedFlight;

    private List<Passenger> passengers;


    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }


    public Flight getBookedFlight() {
        return bookedFlight;
    }

    public void setBookedFlight(Flight bookedFlight) {
        this.bookedFlight = bookedFlight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
