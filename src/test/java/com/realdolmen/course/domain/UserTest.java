package com.realdolmen.course.domain;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Test of the User Class
 * @author JBCBF07
 */

public class UserTest {

    User user;

    @Before
    public void init(){
        user = new User();
    }

    @Test
    public void shouldReturnNullIdWhenNotSaved(){
        assertNull("User ID should be null when not yet saved", user.getId());
    }


}
