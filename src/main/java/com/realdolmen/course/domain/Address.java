package com.realdolmen.course.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * The address of a user is kept in another class, but will be in the same table in the database.
 * @author JBCBF07
 */
@Embeddable
public class Address implements Serializable {
	@NotBlank
    @Size(max = 50) @Column(nullable = false, length = 50)
    private String country;
    @NotBlank
    @Size(max = 50) @Column(nullable = false, length = 50)
    private String street;
    @NotBlank
    @Size(max = 8) @Column(nullable = false, length = 8)
    private String number;
    @NotBlank
    @Size(max = 50) @Column(nullable = false, length = 50)
    private String city;
    @NotBlank
    @Size(max = 8) @Column(nullable = false, length = 8)
    private String zip;

    public Address() {
    }

    public Address(String country, String street, String number, String city, String zip) {
        this.country = country;
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
