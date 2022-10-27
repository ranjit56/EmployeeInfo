package com.employeeinfo.controller.test;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.employeeinfo.controller.UserRegistrationController;
import com.employeeinfo.domain.UserRegistrationDto;
import com.employeeinfo.entity.Employee;
import com.employeeinfo.entity.User;
import com.employeeinfo.repository.EmployeeRepository;
import com.employeeinfo.service.EmployeeService;
import com.employeeinfo.service.UserService;

@SpringBootTest(classes= {UserRegistrationControllerTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationControllerTest {
	
	@InjectMocks
	UserRegistrationController userRegistrationController;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	Model model;
	
	@Mock
	Employee employee;
	
	@Mock
	UserRegistrationDto userRegistrationDto;
	
	@Mock
	UserRegistrationDto registrationDto;
	
	@Mock
	EmployeeService employeeService;
	
	@Mock
	UserService userService;
	
	@Test
	public void userRegistrationDtoTest() throws Exception{
	
		userRegistrationController.userRegistrationDto();
		
	}
	
	@Test
	public void showRegistrationFormTest() throws Exception{
		userRegistrationController.showRegistrationForm();
		
	}
	
	@Test
	public void registerUserAccountTest() throws Exception{
		
	UserRegistrationDto registrationDto = new UserRegistrationDto();
		
		registrationDto.setFirstName("Ranjith");
		registrationDto.setLastName("Indluru");
		registrationDto.setEmail("ranjith@gmail.com");
		registrationDto.setPassword("ran1234@");
		
		userService.save(registrationDto);

		
		userRegistrationController.registerUserAccount(userRegistrationDto);
		
	}
}
