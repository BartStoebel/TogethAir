package com.realdolmen.course.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Min;

@Entity
public class Price implements Serializable {
	static final BigDecimal PROFIT_PERCENTAGE = BigDecimal.valueOf (5.0);
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, scale = 2)
	@Min(value = 0)
	private BigDecimal base;
	
	@Column(nullable = false)
	@Min(value = 0)
	private BigDecimal profitPercentage = PROFIT_PERCENTAGE;
	
	@Column(nullable = false)
	@Min(value = 0)
	private BigDecimal fixBonus = BigDecimal.ZERO;
	
	@Version
	private Long version;
}
