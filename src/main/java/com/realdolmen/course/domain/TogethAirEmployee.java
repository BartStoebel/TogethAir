package com.realdolmen.course.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Subclass of User. Employee of the TogethAir company.
 * @author JBCBF07
 */

@Entity
@DiscriminatorValue("T")
public class TogethAirEmployee extends User {
    public TogethAirEmployee() {
    }

    public TogethAirEmployee(String firstName, String lastName, String password, String email, Integer version) {
        super(firstName, lastName, password, email, version);
    }
}
