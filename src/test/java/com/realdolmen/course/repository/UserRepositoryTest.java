package com.realdolmen.course.repository;

import com.realdolmen.course.AbstractPersistenceTest;
import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.Company;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.utils.DateUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing the User Repository
 * @author JBCBF07
 */

public class UserRepositoryTest extends AbstractPersistenceTest {

    private static final Long   TEST_PERSON_ID = 1L;
    private static final String TEST_PERSON_EMAIL = "joris@test.com";
    private static final Role   TEST_PERSON_ROLE = Role.CLIENT;
    private UserRepository ur;

    @Before
    public void init(){
        ur = new UserRepository();
        ur.em = em;
    }

    @Test
    public void shouldSaveUser(){
        User u = new User(
                "Johnny",
                "De Smedt",
                "password",
                "johnny@test.com",
                new Address(
                        "Belgium",
                        "boekstraat",
                        "25",
                        "Antwerpen",
                        "2000"
                ),
                "+326598875421",
                DateUtils.createDate("1990-12-12 12:12:12"),
                Role.CLIENT,
                new Company(
                        "FlightAirlines",
                        "This is the best airline in the world"
                )
        );
        ur.save(u);
        assertNotNull("User ID is not supposed to be null after saving", u.getId());
    }

    @Test
    public void shouldReturnAllUsers(){
        List<User> users = ur.findAll();
        assertNotNull("User list can't be null", users);
        assertEquals(3, users.size());
    }

    @Test
    public void shouldReturnUserWithId(){
        User u = ur.findById(TEST_PERSON_ID);
        assertNotNull(u);
        assertNotNull(u.getId());
        assertEquals(TEST_PERSON_EMAIL, u.getEmail());
    }

    @Test
    public void shouldReturnUserWithEmail(){
        User u = ur.findByEmail(TEST_PERSON_EMAIL);
        assertNotNull(u);
        assertNotNull(u.getId());
        assertEquals(TEST_PERSON_ID, u.getId());
    }

    @Test
    public void shouldReturnUsersWithRole(){
        List<User> users = ur.findByRole(TEST_PERSON_ROLE);
        assertNotNull(users);
        assertEquals(2, users.size());
        for (User u : users){
            assertEquals(Role.CLIENT, u.getRole());
        }
    }



}
