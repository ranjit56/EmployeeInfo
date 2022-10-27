package com.employeeinfo.controller.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.employeeinfo.controller.MainController;
import com.employeeinfo.entity.Employee;
import com.employeeinfo.repository.EmployeeRepository;
import com.employeeinfo.service.EmployeeService;

@SpringBootTest(classes= {MainControllerTest.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest {
	
	@InjectMocks
	MainController mainController;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	Model model;
	
	@Mock
	Employee employee;
	
	@Mock
	EmployeeService employeeService;
	
	@Test
	public void loginTest() throws Exception{
		mainController.login();
	}

}
