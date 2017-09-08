package com.realdolmen.course.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	public UserRegistrationBean() {
	}

}
