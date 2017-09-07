package com.realdolmen.course.repository;

import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository that will manage all instances of User
 * @author JBCBF07
 */

@Stateless
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(User u){
        if (u.getCompany() != null) em.persist(u.getCompany());
        em.persist(u);
        em.flush();
        return u.getId();
    }

    public User findById(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u order by u.lastName, u.firstName", User.class).getResultList();
    }

    public List<User> findByRole(Role r){
        Query q = em.createQuery("select u from User u where role = :role order by u.lastName, u.firstName", User.class);
        q.setParameter("role", r);
        return q.getResultList();
    }

    public User findByEmail(String email){
        Query q = em.createQuery("select u from User u where u.email = :email order by u.lastName, u.firstName", User.class);
        q.setParameter("email", email);
        return (User) q.getSingleResult();
    }

}
