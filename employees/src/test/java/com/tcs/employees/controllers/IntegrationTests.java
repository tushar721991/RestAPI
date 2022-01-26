package com.tcs.employees.controllers;

import com.tcs.employees.handlers.RecordNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tcs.employees.model.Employee;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTests {

	@Autowired
	EmployeeController employeeController;

	public static final Integer EMPLOYEE_ID = 1;
	@Test
	public void testCreateReadDelete() {
		Employee employee = new Employee("Tushar", "Thakur");

		ResponseEntity<Employee> employeeResult = employeeController.saveEmployee(employee);

		ResponseEntity<Iterable<Employee>> employeesResponseEntity = employeeController.getAllEmployees();
		Assertions.assertThat(employeesResponseEntity.getBody())
				.first().hasFieldOrPropertyWithValue("firstName", "Tushar");

		employeeController.delete(employeeResult.getBody().getId());
		Assertions.assertThat(employeeController.getAllEmployees().getBody()).isEmpty();

	}

	@Test
	public void errorHandlingValidationExceptionThrown() {

		Assertions.assertThatExceptionOfType(RecordNotFoundException.class)
				.isThrownBy(() -> employeeController.getEmployeeById(EMPLOYEE_ID));
	}
}
