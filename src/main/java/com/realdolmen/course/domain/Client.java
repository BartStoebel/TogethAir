package com.realdolmen.course.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Subclass of User. Website user, someone who wants to book a flight.
 * @author JBCBF07
 */

@Entity
@DiscriminatorValue("C")
public class Client extends User {

    @Embedded
    private Address address;
    @Size(max = 15, min = 8)
    private String phoneNumber;
    @NotBlank @Temporal(TemporalType.DATE)
    private Date birthDate;

    public Client() {
    }

    public Client(String firstName, String lastName, String password, String email, Integer version, Address address, String phoneNumber, Date birthDate) {
        super(firstName, lastName, password, email, version);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
