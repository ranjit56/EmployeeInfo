package com.employeeinfo.serviceImpl.test;


import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeeinfo.domain.UserRegistrationDto;
import com.employeeinfo.entity.Role;
import com.employeeinfo.entity.User;
import com.employeeinfo.repository.UserRepository;
import com.employeeinfo.service.UserServiceImpl;

@SpringBootTest(classes = {UserServiceImplTest.class})
//@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserDetails userDetails;

	@Mock
	User user;

	@Mock
	Role role;

	@Mock
	UserRegistrationDto registrationDto;

	@Mock
	BCryptPasswordEncoder passwordEncoder;

	@Test
	public void saveUserTest() throws Exception {

		UserRegistrationDto registrationDto = new UserRegistrationDto();
		registrationDto.setFirstName("Ranjith");
		registrationDto.setLastName("Indluru");
		registrationDto.setEmail("ranjith45@gmail.com");
		registrationDto.setPassword("asdfghj");

		userServiceImpl.save(registrationDto);

	}

	@Test
	public void loadUserByUsernameTest() throws UsernameNotFoundException {

		User user = new User();
		user.setFirstName("Ranjith");
		user.setLastName("Indluru");
		user.setEmail("ranjith@gmail.com");
		user.setPassword("asdfg");
		Role role = new Role();
		role.setId((long) 1);
		role.setName("USER_ROLE");

		Collection<Role> roles = new ArrayList<Role>();
		user.setRoles(roles);
		roles.add(role);

		String username = "ranjith@gmail.com";
		Mockito.when(userRepository.findByEmail(username)).thenReturn(user);
		userServiceImpl.loadUserByUsername(username);

	}
	
//	@Test
//	public void loadUserByUsername_ExceptionTest() throws UsernameNotFoundException {
//
//		User user = new User();
//		user.setFirstName("Ranjith");
//		user.setLastName("Indluru");
//		user.setEmail("ranjith@gmail.com");
//		user.setPassword("asdfg");
//		Role role = new Role();
//		role.setId((long) 1);
//		role.setName("USER_ROLE");
//
//		Collection<Role> roles = new ArrayList<Role>();
//		user.setRoles(roles);
//		roles.add(role);
//
//		String username = "ranjith@gmail.com";
//		
//		Mockito.when(userRepository.findByEmail(username)).thenReturn(null);
//		userServiceImpl.loadUserByUsername(null);
//
//	}

}
