
package com.realdolmen.course.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.service.UserServiceBean;

/**
 * FormClass for loginScreen
 * @author BSEBF08
 *
 */

@Named
@RequestScoped
public class AirlineLoginBean implements Serializable{
	
	@Inject
	private UserServiceBean userService;
	@Inject 
	private LoggedInBean loggedInBean;
	
	private boolean userNotFound = false;
	
	@Email (message = "{req.email}")
	@NotBlank (message = "{req.email}")
	private String email;
	
	@NotBlank (message = "{req.password}")
	@Size(max = 200, message = "{siz.password}")
	private String password;
	
	//Constructor
	public AirlineLoginBean() {
		super();
	}
	
	//Properties
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isUserNotFound() {
		return userNotFound;
	}

	public void setUserNotFound(boolean userNotFound) {
		this.userNotFound = userNotFound;
	}

	public String loginUser() {
		userNotFound = false;
		if (userService.isUserPasswordCorrect(email, password)) {
			User user = userService.findByEmail(email);
			if(user.getRole() == Role.AIRLINE_EMPLOYEE) {
			loggedInBean.setUser(user);
			return "addflight";
			}
		}
			userNotFound = true;
			return "login";
	}

	public String newUser() {
		return "userregistration";
	}
}
