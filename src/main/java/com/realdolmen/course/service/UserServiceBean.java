package com.realdolmen.course.service;

import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.repository.UserRepository;
import com.realdolmen.course.utils.Password;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;



/**
 * Service used to execute actions on the User Repository
 * @author JBCBF07
 */

@Stateless @LocalBean
public class UserServiceBean {

    @EJB
    UserRepository ur;

    /**
     * Save a user to the database
     * @param u
     * @return
     */
    public User save(User u){
        return ur.save(u);
    }

    /**
     * Return a user by id
     * @param id
     * @return
     */
    public User findById(Long id){
        return ur.findById(id);
    }

    /**
     * return all user in the database
     * @return
     */
    public List<User> findAll(){
        return ur.findAll();
    }

    /**
     * return all users that have a certain role
     * @param r
     * @return
     */
    public List<User> findByRole(Role r){
        return ur.findByRole(r);
    }

    /**
     * Find a user by email
     * @param email
     * @return
     */
    public User findByEmail(String email){
    	return ur.findByEmail(email.toLowerCase());
    }

    /**
     * Checks the email and the password of the userlogin. Returns null if 
     * not correct.
     * @param email
     * @return
     */
    public boolean isUserPasswordCorrect(String email, String password) {
    	User user = findByEmail(email);
    	if (user != null && Password.checkPassword(password, user.getPassword())){
    		return true;
    	} else {
    		return false;
    	}
    }
}
