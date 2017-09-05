package com.realdolmen.course.domain;

import com.realdolmen.course.utils.DateUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PersonTest {
    @Test
    public void nameReturnsConcatenationOfFirstNameAndLastName() throws Exception {
        assertEquals("George Orwell", new Person("George", "Orwell", DateUtils.createDate("1970-01-01")).name());
    }

    @Test
    public void personIsInstantiatedWithNullId() throws Exception {
        assertNull("Person ID is supposed to be null before saving", new Person("Paul", "McCartney", DateUtils.createDate("1970-01-01")).getId());
    }
}
