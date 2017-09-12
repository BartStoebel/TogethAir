package com.realdolmen.course.repository;

import com.realdolmen.course.AbstractPersistenceTest;
import com.realdolmen.course.domain.Airport;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Testing the Airport Repo
 * @author JBCBF07
 */


public class AirportRepositoryTest extends AbstractPersistenceTest {

    private static final Long       TEST_AIRPORT_ID = 2L;
    private AirportRepository ar;

    @Before
    public void init(){
        ar = new AirportRepository();
        ar.em = em;
    }

    @Test
    public void shouldSaveAirport(){
        Airport airport = new Airport(
                "test1",
                "test2",
                "test3",
                "test4",
                "test5"
        );
        Long airportID = ar.save(airport);
        assertNotNull(airportID);
        Airport test = ar.findById(airportID);
        assertNotNull(test);
        assertEquals("test2", test.getCountry());
    }

    @Test
    public void shouldFindAirportById(){
        Airport airport = ar.findById(TEST_AIRPORT_ID);
        assertNotNull(airport);
        assertEquals("JFK", airport.getCode());
    }

    @Test
    public void shouldFindAirportsByParam(){
        List<Airport> list = ar.findAllWithCityOrCountryLike("Yor");
        assertNotNull(list);
        assertEquals(2, list.size());
        for (Airport a : list){
            assertNotNull(a);
            assertEquals("New York", a.getCity());
            assertEquals("USA", a.getCountry());
        }
    }

    @Test
    public void shouldFindAllAirports(){
        List<Airport> list = ar.findAll();
        assertNotNull(list);
        assertEquals(6, list.size());
    }
    @Test
    public void shouldFindAirportByCityAndCode() {
    	List<Airport> airports = ar.findAirportsByCityWithCode("New York (JFK)");
    	assertNotNull(airports);
    	Airport airport = airports.get(0);
    	assertEquals("New York", airport.getCity());
    }
    @Test
    public void shouldNotFindAirportByCityAndCode() {
    	List<Airport> airports = ar.findAirportsByCityWithCode("test");
    	assertNull(airports);
    	
    }



}
