package com.realdolmen.course.domain;

import com.realdolmen.course.enums.Role;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Abstract superclass for every type of user that may log into the system.
 * @author JBCBF07
 */

@Entity
public class User implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 200) @Column(nullable = false, length = 200)
    private String firstName;

    @NotBlank @Size(max = 200) @Column(nullable = false, length = 200)
    private String lastName;

    @NotBlank @Size(max = 200) @Column(nullable = false, length = 200)
    private String password;

    @NotBlank @Size(max = 200) @Column(nullable = false, length = 200, unique = true)
    private String email;

    @Embedded
    private Address address;

    @Size(min = 5, max = 22) @Column(length = 25)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private Company company;

    @Version
    private Integer version;

    public User() {
    }

    public User(String firstName, String lastName, String password, String email, Address address, String phoneNumber, Date birthDate, Role role, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.role = role;
        this.company = company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Integer getVersion() {
        return version;
    }

    public Role getRole() {
        return role;
    }
}
