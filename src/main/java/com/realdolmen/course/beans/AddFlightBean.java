package com.realdolmen.course.beans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Price;
import com.realdolmen.course.enums.BudgetClass;
import com.realdolmen.course.service.AirportService;
import com.realdolmen.course.service.FlightService;

@Named
@RequestScoped
public class AddFlightBean implements Serializable {
	@Inject
	private AirportService airportService;
	@Inject
	private LoggedInBean loggedInBean;
	
	private UIComponent fromNotCorrect;
	private UIComponent toNotCorrect;
	
	@Inject
	private FlightService flightService;
	
	private Flight flight = new Flight();
	private List<String> autoCompletePlaces;
	
	@NotBlank
	private String from;
	@NotBlank
	private String to;

	@Min(0)
	private Integer availableFirstClass = 0;
	@Min(0)
	private Integer availableBusiness = 0;
	@Min(0)
	private Integer availableEconomy = 0;
	@Min(0)
	private BigDecimal priceFirstClass = BigDecimal.ZERO;
	@Min(0)
	private BigDecimal priceBusiness = BigDecimal.ZERO;
	@Min(0)
	private BigDecimal priceEconomy = BigDecimal.ZERO;
	@Min(0)
	private int numberPersonsForDiscount;
	
	
	//Construcor
	public AddFlightBean() {
		priceFirstClass.setScale(2,RoundingMode.HALF_UP);
		priceBusiness.setScale(2,RoundingMode.HALF_UP);
		priceEconomy.setScale(2,RoundingMode.HALF_UP);
	}
	
	//@PostConstruct
	@PostConstruct
    public void init(){
        autoCompletePlaces = airportService.getCityWithCodeAutoComplete();
    }

	//Properties
	
	public Flight getFlight() {
		return flight;
	}

	public int getNumberPersonsForDiscount() {
		return numberPersonsForDiscount;
	}

	public void setNumberPersonsForDiscount(int numberPersonsForDiscount) {
		this.numberPersonsForDiscount = numberPersonsForDiscount;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Integer getAvailableFirstClass() {
		return availableFirstClass;
	}

	public void setAvailableFirstClass(Integer availableFirstClass) {
		this.availableFirstClass = availableFirstClass;
	}

	public Integer getAvailableBusiness() {
		return availableBusiness;
	}

	public void setAvailableBusiness(Integer availableBusiness) {
		this.availableBusiness = availableBusiness;
	}

	public Integer getAvailableEconomy() {
		return availableEconomy;
	}

	public void setAvailableEconomy(Integer availableEconomy) {
		this.availableEconomy = availableEconomy;
	}

	public BigDecimal getPriceFirstClass() {
		return priceFirstClass;
	}

	public void setPriceFirstClass(BigDecimal priceFirstClass) {
		this.priceFirstClass = priceFirstClass;
	}

	public BigDecimal getPriceBusiness() {
		return priceBusiness;
	}

	public void setPriceBusiness(BigDecimal priceBusiness) {
		this.priceBusiness = priceBusiness;
	}

	public BigDecimal getPriceEconomy() {
		return priceEconomy;
	}

	public void setPriceEconomy(BigDecimal priceEconomy) {
		this.priceEconomy = priceEconomy;
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
	
	public UIComponent getFromNotCorrect() {
		return fromNotCorrect;
	}

	public void setFromNotCorrect(UIComponent fromNotCorrect) {
		this.fromNotCorrect = fromNotCorrect;
	}

	public UIComponent getToNotCorrect() {
		return toNotCorrect;
	}

	public void setToNotCorrect(UIComponent toNotCorrect) {
		this.toNotCorrect = toNotCorrect;
	}
	
	//Methods
	public List<String> completePlace(String query){
        if (query == null || query.length() <= 0) return new ArrayList<String>();
        List<String> auto = new ArrayList<>();
        for (String s : autoCompletePlaces){
            if (s.toLowerCase().contains(query.toLowerCase())) auto.add(s);
        }
        return auto;
    }
	
	public String save() {
		//set the company
		flight.setCompany(loggedInBean.getUser().getCompany());
		//set the price per budgetclass
		setPricePerBudgetClass(BudgetClass.FIRST_CLASS, priceFirstClass);
		setPricePerBudgetClass(BudgetClass.ECONOMY, priceEconomy);
		setPricePerBudgetClass(BudgetClass.BUSINESS, priceBusiness);
		//set the availableSeats per budgetclass
		setAvailableSeatsPerBudgetClass(BudgetClass.FIRST_CLASS, availableFirstClass);
		setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, availableBusiness);
		setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, availableEconomy);
		//set the from
		Airport airportFrom = airportService.findAirportByCityWithCode(from);
		Airport airportTo = airportService.findAirportByCityWithCode(to);
		if (airportFrom == null) {
			FacesContext context = FacesContext.getCurrentInstance();
		    context.addMessage(fromNotCorrect.getClientId(), new FacesMessage("Please choose an existing airport, by typing the first letters.") );
			return "";
		}
		if(airportTo == null) {
			FacesContext context = FacesContext.getCurrentInstance();
		    context.addMessage(toNotCorrect.getClientId(), new FacesMessage("Please choose an existing airport, by typing the first letters.") );
			return "";
		}
		flight.setAirportFrom(airportFrom);
		flight.setAirportTo(airportTo);
		
		flightService.save(flight);
		
		return "addVolumeDiscount";
	}
	/**
	 * Price can be 0. Free fligt, but we can override this price for profit
	 * @param budgetClass
	 * @param pricePerBudget
	 */
	public void setPricePerBudgetClass(BudgetClass budgetClass, BigDecimal pricePerBudget) {
		if (pricePerBudget.compareTo(BigDecimal.ZERO) == 1 || pricePerBudget.compareTo(BigDecimal.ZERO) == 0) {
			Price price = new Price();
			price.setBase(pricePerBudget);
			flight.setPricePerBudgetClass(budgetClass, price);
		}
	}
	/**
	 * If the available seats for this BudgetClass are > 0, persist them in the database
	 * @param budgetClass
	 * @param available
	 */
	public void setAvailableSeatsPerBudgetClass(BudgetClass budgetClass, int available) {
		if (available > 0) {
			flight.setAvailableSeatsPerBudgetClass(budgetClass, available);
		}		
	}
}
