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
import javax.validation.constraints.Max;
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
	@Inject
	private FlightService flightService;
	//@Inject
	private Flight flight = new Flight();

	private List<String> autoCompletePlaces;
	private UIComponent fromNotCorrect;
	private UIComponent toNotCorrect;
	
	private String saveSuccess = null;
	private String saveFailed = null;

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
	private Integer numberPersonsForDiscount1 = 0;
	@Min(0)
	private Integer numberPersonsForDiscount2 = 0;
	@Min(0)
	private Integer numberPersonsForDiscount3 = 0;
	@Min(0)
	private Integer numberPersonsForDiscount4 = 0;
	@Min(0) @Max(100)
	private BigDecimal groupDiscount1 = BigDecimal.ZERO;
	@Min(0) @Max(100)
	private BigDecimal groupDiscount2 = BigDecimal.ZERO;
	@Min(0) @Max(100)
	private BigDecimal groupDiscount3 = BigDecimal.ZERO;
	@Min(0) @Max(100)
	private BigDecimal groupDiscount4 = BigDecimal.ZERO;

	// Construcor
	public AddFlightBean() {
		priceFirstClass.setScale(2, RoundingMode.HALF_UP);
		priceBusiness.setScale(2, RoundingMode.HALF_UP);
		priceEconomy.setScale(2, RoundingMode.HALF_UP);
	}

	// @PostConstruct
	@PostConstruct
	public void init() {
		autoCompletePlaces = airportService.getCityWithCodeAutoComplete();
	}

	// Properties

	public Flight getFlight() {
		return flight;
	}

	public String getSaveFailed() {
		return saveFailed;
	}

	public void setSaveFailed(String saveFailed) {
		this.saveFailed = saveFailed;
	}

	public int getNumberPersonsForDiscount2() {
		return numberPersonsForDiscount2;
	}

	public void setNumberPersonsForDiscount2(int numberPersonsForDiscount2) {
		this.numberPersonsForDiscount2 = numberPersonsForDiscount2;
	}

	public int getNumberPersonsForDiscount3() {
		return numberPersonsForDiscount3;
	}

	public void setNumberPersonsForDiscount3(int numberPersonsForDiscount3) {
		this.numberPersonsForDiscount3 = numberPersonsForDiscount3;
	}

	public int getNumberPersonsForDiscount4() {
		return numberPersonsForDiscount4;
	}

	public void setNumberPersonsForDiscount4(int numberPersonsForDiscount4) {
		this.numberPersonsForDiscount4 = numberPersonsForDiscount4;
	}

	public BigDecimal getGroupDiscount2() {
		return groupDiscount2;
	}

	public void setGroupDiscount2(BigDecimal groupDiscount2) {
		this.groupDiscount2 = groupDiscount2;
	}

	public BigDecimal getGroupDiscount3() {
		return groupDiscount3;
	}

	public void setGroupDiscount3(BigDecimal groupDiscount3) {
		this.groupDiscount3 = groupDiscount3;
	}

	public BigDecimal getGroupDiscount4() {
		return groupDiscount4;
	}

	public void setGroupDiscount4(BigDecimal groupDiscount4) {
		this.groupDiscount4 = groupDiscount4;
	}

	public BigDecimal getGroupDiscount1() {
		return groupDiscount1;
	}

	public void setGroupDiscount1(BigDecimal groupDiscount1) {
		this.groupDiscount1 = groupDiscount1;
	}

	public int getNumberPersonsForDiscount1() {
		return numberPersonsForDiscount1;
	}

	public void setNumberPersonsForDiscount1(int numberPersonsForDiscount) {
		this.numberPersonsForDiscount1 = numberPersonsForDiscount;
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

	public String getSaveSuccess() {
		return saveSuccess;
	}

	public void setSaveSuccess(String saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

	// Methods
	public List<String> completePlace(String query) {
		if (query == null || query.length() <= 0)
			return new ArrayList<String>();
		List<String> auto = new ArrayList<>();
		for (String s : autoCompletePlaces) {
			if (s.toLowerCase().contains(query.toLowerCase()))
				auto.add(s);
		}
		return auto;
	}
	/**
	 * Save the flight, with all the values of addflight.xhtml
	 * 
	 * @return
	 */
	public String save() {
		// set the company
		flight.setCompany(loggedInBean.getUser().getCompany());
		// set the price per budgetclass
		setPricePerBudgetClass(BudgetClass.FIRST_CLASS, priceFirstClass);
		setPricePerBudgetClass(BudgetClass.ECONOMY, priceEconomy);
		setPricePerBudgetClass(BudgetClass.BUSINESS, priceBusiness);
		// set the availableSeats per budgetclass
		setAvailableSeatsPerBudgetClass(BudgetClass.FIRST_CLASS, availableFirstClass);
		setAvailableSeatsPerBudgetClass(BudgetClass.BUSINESS, availableBusiness);
		setAvailableSeatsPerBudgetClass(BudgetClass.ECONOMY, availableEconomy);
		//add volumeDiscounts to object flight
		addVolumeDiscount(numberPersonsForDiscount1, groupDiscount1);
		addVolumeDiscount(numberPersonsForDiscount2, groupDiscount2);
		addVolumeDiscount(numberPersonsForDiscount3, groupDiscount3);
		addVolumeDiscount(numberPersonsForDiscount4, groupDiscount4);
		// set the from
		Airport airportFrom = airportService.findAirportByCityWithCode(from);
		Airport airportTo = airportService.findAirportByCityWithCode(to);
		saveFailed = null;
		saveSuccess = null; 
		if (airportFrom == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(fromNotCorrect.getClientId(),
					new FacesMessage("Please choose an existing airport, by typing the first letters."));
			return "";
		}
		if (airportTo == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(toNotCorrect.getClientId(),
					new FacesMessage("Please choose an existing airport, by typing the first letters."));
			return "";
		}
		flight.setAirportFrom(airportFrom);
		flight.setAirportTo(airportTo);
		try {
			Flight save = flightService.save(flight);
			saveSuccess = "Previous flight " + flight.getName() + " for date " + flight.getDepartureTime() + 
					" was successful. You can now enter a new one, or log out.";
			flight = new Flight();
			resetValues();
		} catch (Exception e) {
			saveFailed = "Previous flight \"" + flight.getName() + "\" for date " + flight.getDepartureTime() + 
					" was NOT successful. Please contact the administrator";
		}
		return "";
	}

	/**
	 * Price can be 0. Free fligt, but we can override this price for profit
	 * 
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
	 * If the available seats for this BudgetClass are > 0, persist them in the
	 * database
	 * 
	 * @param budgetClass
	 * @param available
	 */
	public void setAvailableSeatsPerBudgetClass(BudgetClass budgetClass, int available) {
		if (available > 0) {
			flight.setAvailableSeatsPerBudgetClass(budgetClass, available);
		}
	}
	public void addVolumeDiscount(Integer numberOfPersons, BigDecimal groupDiscount) {
		if(numberOfPersons > 0) {
			flight.addVolumeDiscount(numberOfPersons, groupDiscount);
		}
	}
	public void resetValues() {
		fromNotCorrect = null;
		toNotCorrect = null;

		from = "";
		to = "";

		availableFirstClass = 0;
		availableBusiness = 0;
		availableEconomy = 0;
		priceFirstClass = BigDecimal.ZERO;
		priceBusiness = BigDecimal.ZERO;
		priceEconomy = BigDecimal.ZERO;
		numberPersonsForDiscount1 = 0;
		numberPersonsForDiscount2 = 0;
		numberPersonsForDiscount3 = 0;
		numberPersonsForDiscount4 = 0;
		groupDiscount1 = BigDecimal.ZERO;
		groupDiscount2 = BigDecimal.ZERO;
		groupDiscount3 = BigDecimal.ZERO;
		groupDiscount4 = BigDecimal.ZERO;
	}
}
