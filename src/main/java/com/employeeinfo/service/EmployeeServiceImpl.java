package com.employeeinfo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employeeinfo.entity.Employee;
import com.employeeinfo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public List<Employee> getAllEmployees() {
		List<Employee> employee = employeeRepository.findAll();
		System.out.println("Getting the data from the Test: "+ employee );
		return employee;
	}

	@Transactional
	public Employee saveEmployee(Employee employee) {
	return employeeRepository.saveAndFlush(employee);
	}

	@Transactional
	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id).orElse(null);

	}

	@Transactional
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Transactional
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}
}
