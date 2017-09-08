package com.realdolmen.course.beans;

import com.realdolmen.course.enums.BudgetClass;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This bean will be used when searching for available flights from the webpage.
 * @author JBCBF07
 */

@Named @SessionScoped
public class SearchFlightsBean implements Serializable {

    @NotBlank(message = "test")
    private String from;


    private String to;


    private Integer numberOfPassengers;


    private Date depatureDate;


    private Date returnDate;


    private BudgetClass budgetClass;


    private BudgetClass[] budgetClasses = BudgetClass.values();


    // Start methods


    public String search(){
        return "test";
    }


    // End methods


    public SearchFlightsBean() {
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
