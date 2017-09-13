package com.realdolmen.course.beans;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Price;
import com.realdolmen.course.domain.VolumeDiscount;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.service.AirportService;
import com.realdolmen.course.service.FlightService;
import org.hibernate.validator.constraints.NotBlank;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostLoad;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * This bean will be used when searching for available flights from the webpage.
 * @author JBCBF07
 */

@Named @SessionScoped
public class SearchFlightsBean implements Serializable {

    @EJB
    private FlightService flightService;

    @EJB
    private AirportService airportService;

    @Inject
    private BookingBean bookingBean;

    @NotBlank(message = "{req.start.location}")
    private String from;

    @NotBlank(message = "{req.destination}")
    private String to;

    @Min(1)
    @NotNull(message = "{req.number.of.passengers}")
    private Integer numberOfPassengers;

    @NotNull(message = "{please.enter.a.date}")
    @Future(message = "{date.not.in.future}")
    private Date departureDate;

    @Future(message = "{date.not.in.future}")
    private Date returnDate;

    private List<Flight> flights;
    private List<Flight> returnFlights;

    private List<String> autoCompletePlaces;


    private BudgetClass budgetClass;


    private BudgetClass[] budgetClasses = BudgetClass.values();

    @PostConstruct
    public void init(){
        autoCompletePlaces = airportService.getPlaceAutoComplete();
    }

    // Start methods

    /**
     * returns the set of Strings that should be displayed in autocomplete. Using the query that is typed in the input.
     * @param query
     * @return
     */
    public List<String> completePlace(String query){
        if (query == null || query.length() <= 0) return new ArrayList<String>();
        List<String> auto = new ArrayList<>();
        for (String s : autoCompletePlaces){
            if (s.toLowerCase().contains(query.toLowerCase())) auto.add(s);
        }
        return auto;
    }

    /**
     * Look for the available flights, giving the parameters chosen by the user. Sort the flights according to price.
     * @return
     */
    public String search(){

        flights = flightService.searchForAvailableFlights(from, to, numberOfPassengers, budgetClass, departureDate);
        if (returnDate != null) returnFlights = flightService.searchForAvailableFlights(to, from, numberOfPassengers, budgetClass, returnDate);

        //Sorting flights
        flights.sort(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                BigDecimal price1 = o1.getPrices().get(budgetClass).calculatePrice();
                BigDecimal price2 = o2.getPrices().get(budgetClass).calculatePrice();
                return price1.compareTo(price2);
            }
        });

        return "searchresult";
    }

    /**
     * display time in a readable fashion
     * @param date
     * @return
     */
    public String displayTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    /**
     * display date in a readable fashion
     * @param date
     * @return
     */
    public String displayDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd MMMM yyyy");
        return simpleDateFormat.format(date);
    }

    /**
     * calculate the price of a given flight with discounts
     * @param flight
     * @param price
     * @return
     */
    public BigDecimal calcPriceWithDiscount(Flight flight, Price price){
        BigDecimal amount = price.calculatePrice();
        BigDecimal perc = BigDecimal.ZERO;
        Integer runner = numberOfPassengers;
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
        amount = amount.multiply(BigDecimal.valueOf(numberOfPassengers));
        return amount;
    }

    /**
     * calculate the price of a given flight without discounts
     * @param price
     * @return
     */
    public BigDecimal calcPriceWithoutDiscount(Price price){
        BigDecimal value = price.calculatePrice();
        value = value.multiply(BigDecimal.valueOf(numberOfPassengers));
        return value;
    }

    /**
     * returns boolean whether the flight has discount
     * @param flight
     * @param price
     * @return
     */
    public boolean hasDiscount(Flight flight, Price price){
        Integer runner = numberOfPassengers;
        while(runner > 0){
        	if (flight.getVolumeDiscounts().containsKey(runner)) {
        		return true;
        	}
            runner--;
        }
        return false;
    }

    /**
     * choose a flight and set it as booked flight. navigate to inputPassengers.xhtml OR login.xhtml if not logged in
     * @param id
     * @param logged
     * @return
     */
    public String chooseFlight(Long id, boolean logged){
        if (bookingBean.getBooking() != null) return "alreadyhavebooking";
        bookingBean.setBookedFlight(flightService.findById(id));
        bookingBean.setPassengers(new ArrayList<>());
        for(int i = 0; i < numberOfPassengers; i++){
            bookingBean.addPassenger(new Passenger());
        }
        bookingBean.setBudgetClass(budgetClass);
        if (logged) return "inputPassengers";
        return "login";
    }





    // End methods

    public SearchFlightsBean() {
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getFrom() {
        return from;
    }

    public BudgetClass[] getBudgetClasses() {
        return budgetClasses;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BudgetClass getBudgetClass() {
        return budgetClass;
    }

    public void setBudgetClass(BudgetClass budgetClass) {
        this.budgetClass = budgetClass;
    }

    public List<Flight> getReturnFlights() {
        return returnFlights;
    }

    public void setReturnFlights(List<Flight> returnFlights) {
        this.returnFlights = returnFlights;
    }

    public FlightService getFlightService() {
        return flightService;
    }

    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public AirportService getAirportService() {
        return airportService;
    }

    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    public BookingBean getBookingBean() {
        return bookingBean;
    }

    public void setBookingBean(BookingBean bookingBean) {
        this.bookingBean = bookingBean;
    }

    public void setBudgetClasses(BudgetClass[] budgetClasses) {
        this.budgetClasses = budgetClasses;
    }

    public List<String> getAutoCompletePlaces() {
        return autoCompletePlaces;
    }

    public void setAutoCompletePlaces(List<String> autoCompletePlaces) {
        this.autoCompletePlaces = autoCompletePlaces;
    }


}
