package com.realdolmen.course.beans;

import com.realdolmen.course.domain.*;
import com.realdolmen.course.enums.BookingStatus;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.enums.PaymentChoice;
import com.realdolmen.course.repository.BookingRepository;
import com.realdolmen.course.service.FlightService;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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

    @EJB private BookingRepository bookingRepository;

    private Flight bookedFlight;

    private List<Passenger> passengers;

    private int seatsReserved = 0;

    private Booking booking;

    private BudgetClass budgetClass;


    /**
     * When the session is destroyed and you have reserved seats. The seats will be released.
     */
    @PreDestroy
    public void preDestroy()/* throws IOException */{
        bookedFlight = flightService.findById(bookedFlight.getId());
        flightService.revokeSeats(passengers.size(), bookedFlight, searchFlightsBean.getBudgetClass());
        System.out.println("Seats have been revoked");
    }

    /**
     * Create a booking and reserve the seats
     * @return
     */
    public String bookFlight(){

        // Check if flight still has enough seats available
        if (flightService.checkIfSeatsAvailable(passengers.size(), bookedFlight, searchFlightsBean.getBudgetClass())){ // Seats are still available


            // Immediately reserve the seats
            flightService.reserveSeats(passengers.size(), bookedFlight, searchFlightsBean.getBudgetClass());
            seatsReserved = passengers.size();
            // Reserved for 20 minutes!
            FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(20*60);

            BigDecimal discountVolumePrice = searchFlightsBean.calcPriceWithoutDiscount(bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())).subtract(searchFlightsBean.calcPriceWithDiscount(bookedFlight, bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())));

            List<Ticket> ticketList = new ArrayList<>();

            booking = new Booking(
                    searchFlightsBean.calcPriceWithDiscount(bookedFlight, bookedFlight.getPrices().get(searchFlightsBean.getBudgetClass())),
                    discountVolumePrice,
                    BigDecimal.ZERO,
                    null,
                    new Date(),
                    loggedInBean.getUser(),
                    ticketList,
                    BookingStatus.RESERVED
            );

            //bookingRepository.save(booking);



        } else { // Seats are no longer available

            System.out.println("No longer available");
            return "noMoreSeats";

        }



        return "booking";
    }

    /**
     * Create the tickets of the booking and send it permanently to the database
     * @param paymentChoice
     * @return
     */
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
                    flightService.findById(bookedFlight.getId())
            ));

        }

        booking.setTickets(tickets);
        bookingRepository.save(booking);

        booking = null;
        bookedFlight = null;
        passengers = null;
        seatsReserved = 0;

        FacesContext.getCurrentInstance().getExternalContext().setSessionMaxInactiveInterval(7*60*60*24);




        return "thankyou";
    }

    /**
     * When a booking has been made and the seats reserved, the user may still choose to cancel the booking before paying.
     * The booking is deleted and the seats released.
     * @return
     */
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


    /**
     * Calculate the price of the booking with discounts
     * @param flight
     * @param price
     * @return
     */
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

    /**
     * Calculate the price of the booking without discounts
     * @param price
     * @return
     */
    public BigDecimal calcPriceWithoutDiscount(Price price){
        BigDecimal value = price.calculatePrice();
        value = value.multiply(BigDecimal.valueOf(passengers.size()));
        return value;
    }

    /**
     * Returns a boolean whether a discount is available
     * @param flight
     * @param price
     * @return
     */
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
