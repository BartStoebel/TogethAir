package com.realdolmen.course.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.service.UserServiceBean;
import com.realdolmen.course.utils.Password;

/**
 * FormClass for userRegistration
 * @author BSEBF08
 *
 */

@Named
@RequestScoped
public class UserRegistrationBean implements Serializable{
	@Inject
	private UserServiceBean userServiceBean;
	
	
	private User user = new User();
	
	@NotBlank 
    @Size(max = 20) 
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%./]).{6,20})", message="{user.password}")
	private String password;
	
	@NotBlank 
    @Size(max = 20)
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%./]).{6,20})", message="{user.password}")
	private String passwordCheck;
	
	private UIComponent passwordNotEqual;
	private UIComponent emailExistsInDb;
	
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public UIComponent getEmailExistsInDb() {
		return emailExistsInDb;
	}

	public void setEmailExistsInDb(UIComponent emailExistsInDb) {
		this.emailExistsInDb = emailExistsInDb;
	}

	//methods
	public String saveUser() {
		if (checkPassword()) {
			user.setRole(Role.CLIENT);
			if(userServiceBean.findByEmail(user.getEmail()) == null) {
				//user does not exist
				user.setPassword(Password.hashPassword(getPassword()));
				userServiceBean.save(user);
			} else {
				//user exists
				FacesContext context = FacesContext.getCurrentInstance();
			    context.addMessage(emailExistsInDb.getClientId(), new FacesMessage("Email already exists. Choose a unique email"));
			    return "";
			}
				
			
		} else {
			 FacesContext context = FacesContext.getCurrentInstance();
		     context.addMessage(passwordNotEqual.getClientId(), new FacesMessage("Both passwords must be equal!"));
		     return "";
		}
		return "login";
	}
	public boolean checkPassword(){
		if (getPasswordCheck().equals(getPassword())) {
			return true;
		}
		return false;
	}
}
