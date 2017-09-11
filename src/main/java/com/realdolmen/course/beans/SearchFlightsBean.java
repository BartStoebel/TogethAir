package com.realdolmen.course.beans;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Price;
import com.realdolmen.course.domain.VolumeDiscount;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.service.FlightService;
import org.hibernate.validator.constraints.NotBlank;


import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Inject
    private BookingBean bookingBean;

    @NotBlank(message = "{req.start.location}")
    private String from;

    @NotBlank(message = "{req.destination}")
    private String to;

    //@Digits(integer = 2, fraction = 0, message = "{passengers.needs.number}")
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


    private BudgetClass budgetClass;


    private BudgetClass[] budgetClasses = BudgetClass.values();


    // Start methods

    public String search(){
        flights = flightService.searchForAvailableFlights(from, to, numberOfPassengers, budgetClass, departureDate);
        if (returnDate != null) returnFlights = flightService.searchForAvailableFlights(to, from, numberOfPassengers, budgetClass, returnDate);

        return "searchresult";
    }

    public String displayTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    public String displayDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd MMMM yyyy");
        return simpleDateFormat.format(date);
    }

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
        //amount.subtract(amount.divide(BigDecimal.valueOf(100)).multiply(perc));
        return amount;
    }

    public BigDecimal calcPriceWithoutDiscount(Price price){
        BigDecimal value = price.calculatePrice();
        value = value.multiply(BigDecimal.valueOf(numberOfPassengers));
        return value;
    }

    public boolean hasDiscount(Flight flight, Price price){
        //BigDecimal amount = price.calculatePrice();
        //BigDecimal perc = BigDecimal.ZERO;
        Integer runner = numberOfPassengers;
        while(runner > 0){
        	if (flight.getVolumeDiscounts().containsKey(runner)) {
        		//perc = flight.getVolumeDiscounts().get(runner);
        		return true;
        		//break;
        	}
            runner--;
        }
        return false;
    }

    public String chooseFlight(Long id, boolean logged){
        bookingBean.setBookedFlight(flightService.findById(id));
        bookingBean.setPassengers(new ArrayList<>());
        for(int i = 0; i < numberOfPassengers; i++){
            bookingBean.addPassenger(new Passenger());
        }
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
}
