package com.realdolmen.course.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.Company;
import com.realdolmen.course.domain.User;
import com.realdolmen.course.enums.Role;
import com.realdolmen.course.repository.UserRepository;
import com.realdolmen.course.utils.DateUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceBeanTest {
	
	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private UserServiceBean service = new UserServiceBean();
	
	@Before
	public void setupMox() {
		User user = new User(
                "Johnny",
                "De Smedt",
                "$2a$13$aMwCHJMZvQAOn4fOkVfPoe/CU2IgPB4sc2zWFsivCrm81yv7u./Ta", //"password"
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
		List<User> users = new ArrayList<>();
		users.add(user);
		when(repository.findByEmail("johnny@test.com")).thenReturn(user);
		when(repository.findAll()).thenReturn(users);
	}
	
	@After
	public void tearDown() throws Exception {
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	public void shouldFindAllUsers() {
		List<User> users = service.findAll();
		assertNotNull(users);
		assertFalse(users.isEmpty());
		verify(repository).findAll();
	}
	@Test
	public void shouldFindAUserByEmail() {
		User user = service.findByEmail("johnny@test.com");
		assertNotNull(user);
		verify(repository).findByEmail("johnny@test.com");
	}
	@Test
	public void shouldCreateAUser() {
		User user1 = new User(
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
		service.save(user1);
		verify(repository).save(same(user1));
	}
	
	
	
	
	
	
	
	
}
