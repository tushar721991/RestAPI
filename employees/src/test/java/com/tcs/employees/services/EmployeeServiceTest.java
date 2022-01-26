package com.tcs.employees.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.employees.dao.EmployeeRepository;
import com.tcs.employees.model.Employee;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest
{
	@InjectMocks
    EmployeeService service;
     
    @Mock
    EmployeeRepository dao;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindAllEmployees()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee("John", "John");
        Employee empTwo = new Employee("Alex", "kolenchiski");
        Employee empThree = new Employee("Steve", "Waugh");
         
        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);
         
        when(dao.findAll()).thenReturn(list);
         
        List<Employee> empList = service.getAllEmployees();
         
        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }
    
    @Test
    void testCreateOrSaveEmployee()
    {
        Employee employee = new Employee("Tushar","Thakur");
         
        service.saveEmployee(employee);
         
        verify(dao, times(1)).save(employee);
    }
}
