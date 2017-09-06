package com.realdolmen.course.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Abstract superclass for every type of user that may log into the system.
 * @author JBCBF07
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Superclass and subclasses will be mapped to 1 single table, for optimal performance.
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.CHAR)
public abstract class User {
    @NotBlank @Size(max = 200) @Column(nullable = false, length = 200)
    protected String firstName;
    @NotBlank @Size(max = 200) @Column(nullable = false, length = 200)
    protected String lastName;
    @NotBlank @Size(max = 200) @Column(nullable = false, length = 200)
    protected String password;
    @Id @NotBlank @Size(max = 200) @Column(nullable = false, length = 200)
    protected String email;
    @Version
    protected Integer version;


    public User() {
    }

    public User(String firstName, String lastName, String password, String email, Integer version) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
