package com.realdolmen.course.enums;

/**
 * BudgetClass contains all the kinds of tickets we sell.
 * CAUTION!! Do never change the order of the values ... they are mapped numerically
 * in the database, so if the ordering changes, this will initiate inconsistent data!
 * 
 * @author BSEBF08
 *
 */
public enum BudgetClass {
	ECONOMY, FIRST_CLASS, BUSINESS
}
