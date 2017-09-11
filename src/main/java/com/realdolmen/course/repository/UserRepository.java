package com.realdolmen.course.repository;

import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
        TypedQuery<User> q = em.createQuery("select u from User u where u.email = :email order by u.lastName, u.firstName", User.class);
        q.setParameter("email", email);
        List<User> resultList = q.getResultList();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

}
