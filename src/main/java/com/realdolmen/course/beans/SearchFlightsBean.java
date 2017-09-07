package com.realdolmen.course.beans;

import com.realdolmen.course.enums.BudgetClass;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 * This bean will be used when searching for available flights from the webpage.
 * @author JBCBF07
 */

@Named @SessionScoped
public class SearchFlightsBean implements Serializable {

    private String from;
    private String to;
    private Date depatureDate;
    private Integer numberOfPassengers;
    private Date returnDate;
    private BudgetClass budgetClass;


    public SearchFlightsBean() {
    }

    public String getFrom() {
        return from;
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

    public Date getDepatureDate() {
        return depatureDate;
    }

    public void setDepatureDate(Date depatureDate) {
        this.depatureDate = depatureDate;
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
