package com.tcs.employees.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcs.employees.model.Employee;
import com.tcs.employees.services.EmployeeService;

import javax.validation.ValidationException;

/*
* This Controller class has various API methods which are developed for test purpose.
* We have implemented swagger for API documentation.
* */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @PostMapping
  @ApiOperation(value="Save Employee",
          notes = "Provide an Employee to save in database",
          response = ResponseEntity.class)
  public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)  {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(employeeService.saveEmployee(employee));
  }

  @GetMapping
  @ApiOperation(value="Get All Employees",
          notes = "Returns all the employees",
          response = ResponseEntity.class
  )
  public ResponseEntity<Iterable<Employee>> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @GetMapping("/{id}")
  @ApiOperation(value="Get Employee by id",
          notes = "Provide an id to look up specific employee from existing employees",
          response = ResponseEntity.class
  )
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
    return ResponseEntity.ok(employeeService.getEmployeeById(id));
  }

  @PutMapping("/")
  @ApiOperation(value="Update Employee",
          notes = "Provide an employee object to update specific employee",
          response = ResponseEntity.class
  )
  public ResponseEntity<Employee> update(@RequestBody Employee employee) {
    return ResponseEntity.ok(employeeService.updateEmployee(employee));
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value="Delete Employee",
          notes = "Provide an employee id to delete specific employee"
  )
  public void delete(@PathVariable Integer id) {
    employeeService.deleteById(id);
  }

  @GetMapping("/wrong")
  @ApiOperation(value="Wrong API",
          notes = "This Api is to test exception handling"
  )
  public ResponseEntity<Employee> somethingIsWrong() {
    throw new ValidationException("Something is wrong");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  String exceptionHandler(ValidationException e) {
    return e.getMessage();
  }

}
