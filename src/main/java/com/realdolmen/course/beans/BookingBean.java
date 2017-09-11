package com.realdolmen.course.beans;

import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.service.FlightService;
import org.hibernate.Session;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Past;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * This bean will hold the information about a booking.
 * @author JBCBF07
 */

@Named @SessionScoped
public class BookingBean implements Serializable {

    @Inject
    private SearchFlightsBean searchFlightsBean;

    @Inject
    private LoggedInBean loggedInBean;

    @EJB
    private FlightService flightService;

    private Flight bookedFlight;

    private List<Passenger> passengers;

    private int seatsReserved = 0;

    private Booking booking;


    @PreDestroy
    public void preDestroy() throws IOException {
        bookedFlight = flightService.findById(bookedFlight.getId());
        flightService.revokeSeats(passengers.size(), bookedFlight, searchFlightsBean.getBudgetClass());
        System.out.println("Seats have been revoked");
    }

    public String bookFlight(){

        // Check if flight still has enough seats available
        if (flightService.checkIfSeatsAvailable(passengers.size(), bookedFlight, searchFlightsBean.getBudgetClass())){ // Seats are still available


            // Immediately reserve the seats
            flightService.reserveSeats(passengers.size(), bookedFlight, searchFlightsBean.getBudgetClass());
            seatsReserved = passengers.size();
            // Reserved for 60 seconds!
            FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(60);

            BigDecimal discountVolumePrice = searchFlightsBean.calcPriceWithDiscount(bookedFlight, bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())).subtract(searchFlightsBean.calcPriceWithoutDiscount(bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())));


            booking = new Booking(
                    searchFlightsBean.calcPriceWithDiscount(bookedFlight, bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())),
                    discountVolumePrice,
                    BigDecimal.ZERO,
                    null,
                    new Date(),
                    loggedInBean.getUser(),
                    BookingStatus.RESERVED
            );

        } else { // Seats are no longer available

            System.out.println("No longer available");
            return "noMoreSeats";

        }



        return "index";
    }




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
