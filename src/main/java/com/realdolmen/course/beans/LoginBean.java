package com.realdolmen.course.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.realdolmen.course.service.PersonServiceBean;

/**
 * FormClass for loginScreen
 * @author BSEBF08
 *
 */

@Named
@SessionScoped
public class LoginBean implements Serializable{
	@Inject
	private PersonServiceBean personService;
	
	@Email @NotBlank
	private String email;
	
	@NotBlank
	private String password;

	
	//Constructor
	public LoginBean() {
		super();
	}
	
	//Properties
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		System.out.println(email);
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println(password);
		this.password = password;
	}
	
	public void search() {
		System.out.println("BOE !");
	}
	
}
