package com.samaddico.jacoco.employeeapp.service;

import com.samaddico.jacoco.employeeapp.model.Employee;
import com.samaddico.jacoco.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addNew(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteEmployee(Employee employee) {

    }
}
