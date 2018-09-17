package com.samaddico.jacoco.employeeapp.service;

import com.samaddico.jacoco.employeeapp.model.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee addNew(Employee employee);

    public List<Employee> listAll();

    public void delete(Long id);

    public void deleteEmployee(Employee employee);
}
