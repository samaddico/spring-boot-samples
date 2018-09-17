package com.samaddico.jacoco.employeeapp.repository;

import com.samaddico.jacoco.employeeapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
