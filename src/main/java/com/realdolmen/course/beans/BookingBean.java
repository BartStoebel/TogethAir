package com.realdolmen.course.beans;

import com.realdolmen.course.domain.*;
import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.enums.PaymentChoice;
import com.realdolmen.course.repository.BookingRepository;
import com.realdolmen.course.service.FlightService;
import com.realdolmen.course.service.TicketService;
import org.hibernate.Session;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Past;
import java.awt.print.Book;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This bean will hold the information about a booking.
 * @author JBCBF07
 */

@Named @SessionScoped
public class BookingBean implements Serializable {

    private final String CREDIT_CARD = "CREDIT_CARD", ENDORSEMENT = "ENDORSEMENT";

    @Inject
    private SearchFlightsBean searchFlightsBean;

    @Inject
    private LoggedInBean loggedInBean;

    @EJB
    private FlightService flightService;

    @EJB
    private TicketService ticketService;

    @EJB private BookingRepository bookingRepository;

    private Flight bookedFlight;

    private List<Passenger> passengers;

    private int seatsReserved = 0;

    private Booking booking;

    private BudgetClass budgetClass;


    @PreDestroy
    public void preDestroy()/* throws IOException */{
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
            // Reserved for 20 minutes!
            FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(20*60);

            BigDecimal discountVolumePrice = searchFlightsBean.calcPriceWithoutDiscount(bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())).subtract(searchFlightsBean.calcPriceWithDiscount(bookedFlight, bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())));


            booking = new Booking(
                    searchFlightsBean.calcPriceWithDiscount(bookedFlight, bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())),
                    discountVolumePrice,
                    BigDecimal.ZERO,
                    null,
                    new Date(),
                    loggedInBean.getUser(),
                    BookingStatus.RESERVED
            );

            bookingRepository.save(booking);



        } else { // Seats are no longer available

            System.out.println("No longer available");
            return "noMoreSeats";

        }



        return "booking";
    }

    public String payBooking(String paymentChoice){

        PaymentChoice choice;

        switch (paymentChoice){
            case "CREDIT_CARD":
                choice = PaymentChoice.CREDIT_CARD;
                break;
            case "ENDORSEMENT":
                choice = PaymentChoice.ENDORSEMENT;
                break;
            default:
                throw new IllegalArgumentException();
        }


        booking.setPaymentChoice(choice);
        booking.setBookingStatus(BookingStatus.PAYMENT_PENDING);

        // Create all the tickets
        List<Ticket> tickets = new ArrayList<>();

        BigDecimal ticketPrice = booking.calcFinalPriceWithoutDiscounts();
        ticketPrice = ticketPrice.subtract(booking.getDiscountVolume());
        ticketPrice = ticketPrice.subtract(booking.getDiscountCC());
        ticketPrice = ticketPrice.divide(BigDecimal.valueOf(passengers.size()), BigDecimal.ROUND_HALF_DOWN);

        booking.setBookingStatus(BookingStatus.PAID);

        for (Passenger passenger : passengers){

            tickets.add(new Ticket(
                    ticketPrice,
                    budgetClass,
                    passenger,
                    booking,
                    flightService.findById(bookedFlight.getId())
            ));

        }

        for (Ticket t : tickets){
            t.setFlight(flightService.findById(t.getFlight().getId()));
            ticketService.saveTicket(t);
        }

        booking = null;
        bookedFlight = null;
        passengers = null;
        seatsReserved = 0;

        FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(7*60*60*24);




        return "thankyou";
    }

    public String cancelBooking(){
        bookedFlight = flightService.findById(bookedFlight.getId());
        flightService.revokeSeats(passengers.size(), bookedFlight, searchFlightsBean.getBudgetClass());
        booking = null;
        bookedFlight = null;
        passengers = null;
        seatsReserved = 0;
        FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(7*60*60*24);
        return "index";
    }



    public BigDecimal calcPriceWithDiscount(Flight flight, Price price){
        BigDecimal amount = price.calculatePrice();
        BigDecimal perc = BigDecimal.ZERO;
        Integer runner = passengers.size();
        while(runner > 0){
            if (flight.getVolumeDiscounts().containsKey(runner)) {
                perc = flight.getVolumeDiscounts().get(runner);
                break;
            }
            runner--;
        }
        BigDecimal discountValue = amount.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_FLOOR);
        discountValue = discountValue.multiply(perc);
        amount = amount.subtract(discountValue);
        amount = amount.setScale(2, BigDecimal.ROUND_CEILING);
        amount = amount.multiply(BigDecimal.valueOf(passengers.size()));
        return amount;
    }

    public BigDecimal calcPriceWithoutDiscount(Price price){
        BigDecimal value = price.calculatePrice();
        value = value.multiply(BigDecimal.valueOf(passengers.size()));
        return value;
    }

    public boolean hasDiscount(Flight flight, Price price){
        Integer runner = passengers.size();
        while(runner > 0){
            if (flight.getVolumeDiscounts().containsKey(runner)) {
                return true;
            }
            runner--;
        }
        return false;
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

    public SearchFlightsBean getSearchFlightsBean() {
        return searchFlightsBean;
    }

    public void setSearchFlightsBean(SearchFlightsBean searchFlightsBean) {
        this.searchFlightsBean = searchFlightsBean;
    }

    public LoggedInBean getLoggedInBean() {
        return loggedInBean;
    }

    public void setLoggedInBean(LoggedInBean loggedInBean) {
        this.loggedInBean = loggedInBean;
    }

    public FlightService getFlightService() {
        return flightService;
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(int seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public BudgetClass getBudgetClass() {
        return budgetClass;
    }

    public void setBudgetClass(BudgetClass budgetClass) {
        this.budgetClass = budgetClass;
    }

    public String getCREDIT_CARD() {
        return CREDIT_CARD;
    }

    public String getENDORSEMENT() {
        return ENDORSEMENT;
    }
}
