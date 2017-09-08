package com.realdolmen.course.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.realdolmen.course.domain.User;
import com.realdolmen.course.service.UserServiceBean;

/**
 * FormClass for userRegistration
 * @author BSEBF08
 *
 */

@Named
@RequestScoped
public class UserRegistrationBean {
	@Inject
	private UserServiceBean userServiceBean;
	
	@Inject
	private User user;
	
	@NotBlank 
    @Size(max = 200) 
	private String passwordCheck;
	
	private UIComponent passwordNotEqual;
	
	//Constructor
	public UserRegistrationBean() {
	}

	//Properties
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public UIComponent getPasswordNotEqual() {
		return passwordNotEqual;
	}

	public void setPasswordNotEqual(UIComponent passwordNotEqual) {
		this.passwordNotEqual = passwordNotEqual;
	}

	//methods
	public String saveUser() {
		if (checkPassword()) {
			// ga hier verder
		} else {
			 FacesContext context = FacesContext.getCurrentInstance();
		     context.addMessage(passwordNotEqual.getClientId(), new FacesMessage("Both passwords must be equal!"));
		     return "";
		}
		return "index";
	}
	public boolean checkPassword(){
		if (passwordCheck.equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}
