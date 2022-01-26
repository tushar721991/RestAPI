package com.tcs.employees.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.employees.dao.EmployeeRepository;
import com.tcs.employees.handlers.RecordNotFoundException;
import com.tcs.employees.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		employee = employeeRepository.save(employee);
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

		if (employeeOptional.isPresent()) {
			Employee newEntity = employeeOptional.get();
			newEntity.setFirstName(employee.getFirstName());
			newEntity.setLastName(employee.getLastName());

			newEntity = employeeRepository.save(newEntity);

			return newEntity;
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public Employee getEmployeeById(Integer id) {
		Optional<Employee> optEmployee = employeeRepository.findById(id);
		if(optEmployee.isPresent()) {
			return optEmployee.get();
		}
		throw new RecordNotFoundException("No employee record exist for given id");
	}

	public List<Employee> getAllEmployees() {
		List<Employee> result = (List<Employee>) employeeRepository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}

	public void deleteById(Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

}
