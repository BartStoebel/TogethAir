package com.realdolmen.course.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Subclass of User. An employee of a partner airline. Managed by ERP.
 * @author JBCBF07
 */

@Entity
@DiscriminatorValue("A")
public class AirlineEmployee extends User {

    public AirlineEmployee() {
    }

    public AirlineEmployee(String firstName, String lastName, String password, String email, Integer version) {
        super(firstName, lastName, password, email, version);
    }
}
