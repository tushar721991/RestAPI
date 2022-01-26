package com.tcs.employees;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tcs.employees.model.Employee;

public class SystemTests {

	public static final String BASE_URL = "http://localhost:8080/api/employees";
	@Test
	public void testCreateReadDelete() {
		RestTemplate restTemplate = new RestTemplate();


		Employee employee = new Employee("Tushar", "Thakur");
		ResponseEntity<Employee> entity = restTemplate.postForEntity(BASE_URL, employee, Employee.class);

		Employee[] employees = restTemplate.getForObject(BASE_URL, Employee[].class);
		Assertions.assertThat(employees).extracting(Employee::getFirstName).containsOnly("Tushar");

		restTemplate.delete(BASE_URL + "/" + entity.getBody().getId());
		Assertions.assertThat(restTemplate.getForObject(BASE_URL, Employee[].class)).isEmpty();
	}

	@Test
	public void testErrorHandlingReturnsBadRequest() {

		RestTemplate restTemplate = new RestTemplate();

		String url = BASE_URL + "/wrong";

		try {
			restTemplate.getForEntity(url, String.class);
		} catch (HttpClientErrorException e) {
			Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		}
	}

}
