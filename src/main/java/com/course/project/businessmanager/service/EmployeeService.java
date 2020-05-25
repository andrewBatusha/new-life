package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService extends BasicService<Employee, UUID> {
    boolean isEmployeeExistsWithEmail(String email);
    Long getNumberOfEmployeesByBossEmail(String email);
    List<Employee> getEmployeesByBossEmail(String email);
}
