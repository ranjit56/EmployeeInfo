package com.employeeinfo.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.employeeinfo.controller.EmployeeController;
import com.employeeinfo.entity.Employee;
import com.employeeinfo.repository.EmployeeRepository;
import com.employeeinfo.service.EmployeeService;

@SpringBootTest(classes = { EmployeeControllerTest.class })
//@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	Model model;

	@Mock
	EmployeeService employeeService;

	List<Employee> employees;
	Employee employee;

	@Test
	public void viewHomePageTest() throws Exception {
		int pageNo = 1;
		int pageSize = 5;
		String sortField = "firstName";
		String sortDir = "asc";

		employees = new ArrayList<>();
		employees.add(new Employee(1, "Ranj", "Ind", "Java", "ranjith@gmail.com"));
		employees.add(new Employee(2, "Ranjit", "Indlu", "Java", "ranjith56@gmail.com"));
		employees.add(new Employee(3, "Ranjith", "Indluru", "Java", "ranjith5656@gmail.com"));

		Page<Employee> page = new PageImpl<>(employees);

		Mockito.when(employeeService.findPaginated(pageNo, pageSize, sortField, sortDir)).thenReturn(page);
		employeeController.viewHomePage(model);
	}

	@Test
	public void showNewEmployeeFormTest() throws Exception {

		Employee employee = new Employee();
		employee.setFirstName("Ranjith");
		employee.setLastName("Indluru");
		employee.setDepartment("Java");
		employee.setEmail("ranjith@gmail.com");

		employeeController.showNewEmployeeForm(model);
	}

	@Test
	public void saveEmployeeTest() throws Exception {

		Employee employee = new Employee();
		employee.setFirstName("Subhash");
		employee.setLastName("m");
		employee.setDepartment("Java");
		employee.setEmail("subhash@gmail.com");
		when(employeeService.saveEmployee(employee)).thenReturn(employee);
		employeeController.saveEmployees(employee);

	}

	@Test
	public void UpdateEmployeeIdTest() throws Exception {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("Subhash");
		employee.setLastName("m");
		employee.setDepartment("Java");

		long id = 1;
		employeeController.showFormForUpdate(id, model);
		verify(employeeService, times(1)).getEmployeeById(id);

	}

	@Test
	public void deleteEmployeeIdTest() throws Exception {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("Subhash");
		employee.setLastName("m");
		employee.setDepartment("Java");

		long id = 1;
		employeeController.deleteEmployee(id);
		verify(employeeService, times(1)).deleteEmployeeById(id);
	}

	@Test
	public void findPaginatedTest() throws Exception {

		int pageNo = 1;
		int pageSize = 5;
		String sortField = "firstName";
		String sortDir = "asc";

		employees = new ArrayList<>();
		employees.add(new Employee(1, "Ranj", "Ind", "Java", "ranjith@gmail.com"));
		employees.add(new Employee(2, "Ranjit", "Indlu", "Java", "ranjith56@gmail.com"));
		employees.add(new Employee(3, "Ranjith", "Indluru", "Java", "ranjith5656@gmail.com"));

		Page<Employee> page = new PageImpl<>(employees);

		Mockito.when(employeeService.findPaginated(pageNo, pageSize, sortField, sortDir)).thenReturn(page);

		employeeController.findPagination(pageNo, sortField, sortDir, model);
	}

	@Test
	public void findPaginatedTest_Exception() throws Exception {

		int pageNo = 1;
		int pageSize = 5;
		String sortField = "firstName";
		String sortDir = "desc";

		employees = new ArrayList<>();
		employees.add(new Employee(1, "Ranj", "Ind", "Java", "ranjith@gmail.com"));
		employees.add(new Employee(2, "Ranjit", "Indlu", "Java", "ranjith56@gmail.com"));
		employees.add(new Employee(3, "Ranjith", "Indluru", "Java", "ranjith5656@gmail.com"));

		Page<Employee> page = new PageImpl<>(employees);

		Mockito.when(employeeService.findPaginated(pageNo, pageSize, sortField, sortDir)).thenReturn(page);

		employeeController.findPagination(pageNo, sortField, sortDir, model);

	}
}
