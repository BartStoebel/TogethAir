package com.realdolmen.course.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
/**
 * Testing the Price Class. Calculated price must always be greater than basePrice.
 * It should sometimes be the same.
 * @author BSEBF08
 *
 */
public class PriceTest {
	@Test
    public void calculatePriceWithoutFixBonus() throws Exception {
        Price price = new Price();
        price.setBase(BigDecimal.valueOf(126.32));
		assertTrue(BigDecimal.valueOf(126.32*1.05)
				.compareTo(price.calculatePrice()) == 0);
    }
	@Test
    public void calculatePriceWithFixBonus() throws Exception {
        Price price = new Price();
        price.setBase(BigDecimal.valueOf(126.32));
        price.setFixBonus(BigDecimal.valueOf(32.3));
		assertTrue(BigDecimal.valueOf(126.32 + 32.3)
				.compareTo(price.calculatePrice()) == 0);
		
    }
	@Test
	public void calculatePriceWithNegativeFixBonusShouldReturnBase() throws Exception {
        Price price = new Price();
        price.setBase(BigDecimal.valueOf(126.32));
        price.setFixBonus(BigDecimal.valueOf(-32.3));
        //System.out.println(price.calculatePrice());
		assertTrue(BigDecimal.valueOf(126.32)
				.compareTo(price.calculatePrice()) == 0);
    }
	@Test
	public void calculatePriceWithNegativeProfitPercentageShouldReturnBase() throws Exception {
        Price price = new Price();
        price.setBase(BigDecimal.valueOf(126.32));
        price.setProfitPercentage(BigDecimal.valueOf(-5));
        //System.out.println(price.calculatePrice());
		assertTrue(BigDecimal.valueOf(126.32)
				.compareTo(price.calculatePrice()) == 0);
    }
	
}