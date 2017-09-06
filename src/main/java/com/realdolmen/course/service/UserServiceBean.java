package com.realdolmen.course.service;

import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.repository.UserRepository;

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

    public User save(User u){
        return ur.save(u);
    }

    public User findById(Long id){
        return ur.findById(id);
    }

    public List<User> findAll(){
        return ur.findAll();
    }

    public List<User> findByRole(Role r){
        return ur.findByRole(r);
    }

    public User findByEmail(String email){
        return ur.findByEmail(email);
    }

}
