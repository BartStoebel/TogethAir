package com.realdolmen.course.beans;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.service.FlightService;
import org.hibernate.validator.constraints.NotBlank;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.*;
import java.io.Serializable;
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


    private BudgetClass budgetClass;


    private BudgetClass[] budgetClasses = BudgetClass.values();


    // Start methods


    public String search(){
        flights = flightService.searchForAvailableFlights(from, to, numberOfPassengers, budgetClass, departureDate);
        return "searchresult";
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
}
