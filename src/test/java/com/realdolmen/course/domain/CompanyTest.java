package com.realdolmen.course.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CompanyTest {
	@Test
    public void nameReturnsName() throws Exception {
		Company company = new Company();
		company.setName("Sabena");
        assertEquals("Sabena", company.getName());
    }
	
	@Test
    public void nameNotBlank() throws Exception {
		Company company = new Company();
		company.setName("");
        assertEquals("", company.getName());
    }

    @Test
    public void companyIsInstantiatedWithNullId() throws Exception {
        assertNull("Person ID is supposed to be null before saving", new Company("Sabena", "FlightCompany providing flights for Zaventem/Brussels in the early 90s").getId());
    }
	

}
