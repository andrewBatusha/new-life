package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends BasicRepository<Employee, UUID> {
    Optional<List<Employee>> findEmployeesByBossEmail(String email);
    Long countEmployeesByBossEmail(String email);
    Long countEmployeeWithEmail(String email);
    Optional<Employee> findBoss(UUID id);
    Optional<Employee> findByEmail(String email, String buildingName);
}
