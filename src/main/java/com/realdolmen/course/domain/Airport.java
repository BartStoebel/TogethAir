package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class will hold information about all available airports
 * @author JBCBF07
 */

@Entity
public class Airport implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private String region;

    private String code;

    private String city;

    @Version
    private Integer version;

    public Airport() {
    }

    public Airport(String name, String country, String region, String code, String city) {
        this.name = name;
        this.country = country;
        this.region = region;
        this.code = code;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public Integer getVersion() {
        return version;
    }
}
