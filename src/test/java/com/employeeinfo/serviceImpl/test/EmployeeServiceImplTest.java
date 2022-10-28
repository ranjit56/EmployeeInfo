package com.employeeinfo.serviceImpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employeeinfo.controller.test.MainControllerTest;
import com.employeeinfo.entity.Employee;
import com.employeeinfo.repository.EmployeeRepository;
import com.employeeinfo.service.EmployeeServiceImpl;

@SpringBootTest(classes= {EmployeeServiceImplTest.class})
//@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeRepository employeeRepository;

	List<Employee> employees;
	Employee employeeEntity;

	@Test
	public void saveEmployeeTest() throws Exception {

		Employee employeeEntity = new Employee();
		employeeEntity.setId(1);
		employeeEntity.setFirstName("Ranjith");
		employeeEntity.setLastName("Indluru");
		employeeEntity.setDepartment("Engineering-Java");
		employeeEntity.setEmail("ranjith45@gmail.com");
		when(employeeRepository.saveAndFlush(employeeEntity)).thenReturn(employeeEntity);
		employeeServiceImpl.saveEmployee(employeeEntity);
	}

	@Test
	public void deleteEmployeeTest() throws Exception {
		Employee employeeEntity = new Employee();
		employeeEntity.setId(1);
		employeeEntity.setFirstName("Ranjith");
		employeeEntity.setLastName("Indluru");
		employeeEntity.setDepartment("Engineering-Java");
		employeeEntity.setEmail("ranjith45@gmail.com");
		long id = 1;
		employeeServiceImpl.deleteEmployeeById(id);
		verify(employeeRepository, times(1)).deleteById(id);

	}

	@Test
	public void fetchEmployeeTest() throws Exception {

		Employee employeeEntity = new Employee();
		employeeEntity.setId(1);
		employeeEntity.setFirstName("Ranjith");
		employeeEntity.setLastName("Indluru");
		employeeEntity.setDepartment("Engineering-Java");
		employeeEntity.setEmail("ranjith45@gmail.com");
        long id =1;
	  employeeServiceImpl.getEmployeeById(id);
	  verify(employeeRepository, times(1)).findById(id);
;

	}

	@Test
	public void getAllEmployeesTest() throws Exception {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee(2, "ran", "ind", "java", "dfghj"), new Employee(3, "syam", "ind", "java", "dfghj"))
				.collect(Collectors.toList()));
		assertEquals(2, employeeServiceImpl.getAllEmployees().size());
	}

	@Test
	public void findPaginatedTest() throws Exception {

		int pageNo = 1;
		int pageSize = 5;
		String sortField = "firstName";
		String sortDirection = "asc";

		employees = new ArrayList<>();
		employees.add(new Employee(1, "Ranj", "Ind", "Java", "ranjith@gmail.com"));
		employees.add(new Employee(2, "Ranjit", "Indlu", "Java", "ranjith56@gmail.com"));
		employees.add(new Employee(3, "Ranjith", "Indluru", "Java", "ranjith5656@gmail.com"));

		Page<Employee> page = new PageImpl<>(employees);
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		when(employeeRepository.findAll(pageable)).thenReturn(page);
		employeeServiceImpl.findPaginated(pageNo, pageSize, sortField, sortDirection);

	}
	@Test
	public void findPaginatedTest_Exception() throws Exception {

		int pageNo = 1;
		int pageSize = 5;
		String sortField = "firstName";
		String sortDirection = "desc";

		employees = new ArrayList<>();
		employees.add(new Employee(1, "Ranj", "Ind", "Java", "ranjith@gmail.com"));
		employees.add(new Employee(2, "syam", "Indlu", "Java", "ranjith56@gmail.com"));
		employees.add(new Employee(3, "Esoob", "Indluru", "Java", "ranjith5656@gmail.com"));

		Page<Employee> page = new PageImpl<>(employees);
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending(): Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		when(employeeRepository.findAll(pageable)).thenReturn(page);
		employeeServiceImpl.findPaginated(pageNo, pageSize, sortField, sortDirection);

	}

}
