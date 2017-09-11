package com.realdolmen.course.beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.realdolmen.course.domain.User;

/**
 * This bean will only exist if the user is logged in. It 
 * contains all the required user information.
 * @author BSEBF08
 *
 */

@Named
@SessionScoped
public class LoggedInBean implements Serializable {
	
	private User user;
	
	//Constructors
	public LoggedInBean() {
	}

	public LoggedInBean(User user) {
		super();
		this.user = user;
	}

	//Properties
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//methods
	public String logout() {
		user = null;
		return "index";
	}	

}
