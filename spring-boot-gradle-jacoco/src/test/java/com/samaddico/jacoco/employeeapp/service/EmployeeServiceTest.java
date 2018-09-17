package com.samaddico.jacoco.employeeapp.service;


import com.samaddico.jacoco.employeeapp.model.Employee;
import com.samaddico.jacoco.employeeapp.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
        employee.setFirstName("Xandra");
        employee.setLastName("Ibrahim");
        employee.setEmail("ibra@samaddico.com");
        employee.setRole("Assistant Sale Rep");
        employee.setDepartment("Marketing and Sales");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_addNew(){
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee actual = employeeService.addNew(employee);
        verify(employeeRepository, times(1)).save(any(Employee.class));
        Assert.assertEquals(employee,actual);
    }

    @Test
    public void test_listAll(){
        List<Employee> emplist = new ArrayList<>();
        emplist.add(employee);

        when(employeeRepository.findAll()).thenReturn(emplist);
        List<Employee> actual = employeeService.listAll();
        verify(employeeRepository, times(1)).findAll();
        Assert.assertEquals(emplist.size(),actual.size());
    }

}
