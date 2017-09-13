package com.realdolmen.course.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
/**
 * This class contains all the price info needed for a flight. The base is provided by the 
 * airline companies.
 * The automatic PROFIT_PERCENTAGE is set on 5. This is a percentage, to be added to the base.
 * So standard we will take 5% of profit.
 * If the fixBonus is ZERO, then the price will be calculated with the profitPercentage.
 * If ght fixBonus is not ZERO, the profitPercentage will not be used. The total price will be
 * the sum of base and fixBonus.
 * @author BSEBF08
 *
 */
@Embeddable
public class Price implements Serializable {
	static final BigDecimal PROFIT_PERCENTAGE = BigDecimal.valueOf (5.0);
		
	@Column(nullable = false, scale = 2)
	@Min(value = 0)
	private BigDecimal base;
	
	@Column(nullable = false)
	@Min(value = 0)
	private BigDecimal profitPercentage = PROFIT_PERCENTAGE;
	
	@Column(nullable = false)
	@Min(value = 0)
	private BigDecimal fixBonus = BigDecimal.ZERO;
	
	//Constructors
	public Price() {
	}

	public Price(BigDecimal base, BigDecimal profitPercentage, BigDecimal fixBonus) {
		super();
		this.base = base;
		this.profitPercentage = profitPercentage;
		this.fixBonus = fixBonus;
	}

	//Properties
	public BigDecimal getBase() {
		return base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	public BigDecimal getProfitPercentage() {
		return profitPercentage;
	}

	public void setProfitPercentage(BigDecimal profitPercentage) {
		this.profitPercentage = profitPercentage;
	}

	public BigDecimal getFixBonus() {
		return fixBonus;
	}

	public void setFixBonus(BigDecimal fixBonus) {
		this.fixBonus = fixBonus;
	}

		
	//Methods
	/**
	 * This is the price, which consists of the base price, plus the default percentage, 
	 * OR, the base price plus the fixBonus if this is set by a TogethAir employee.
	 * If the result of the calculation lower than base, base will be returned.
	 * @return
	 */
	public BigDecimal calculatePrice() {
		BigDecimal price = BigDecimal.ZERO;
		if (fixBonus.equals(BigDecimal.ZERO)) {
			price =  base.multiply(BigDecimal.ONE.add(profitPercentage.divide(BigDecimal.valueOf(100))));
		} else {
			price =  base.add(fixBonus);
		}
		if (price.compareTo(base) <= 0) {
			return base;
		} else {
			return price;
		}
	}
}