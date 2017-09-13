package com.realdolmen.course.beans;

import com.realdolmen.course.AbstractPersistenceTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the methods of the search flights bean
 * @author JBCBF07
 */

public class SearchFlightsBeanTest extends AbstractPersistenceTest{

    private SearchFlightsBean bean;

    @Before
    public void init(){
        bean = new SearchFlightsBean();
    }

}
