package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Employee;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.exception.FieldAlreadyExistsException;
import com.course.project.businessmanager.repository.EmployeeRepository;
import com.course.project.businessmanager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Method gets information from Repository for the employee with id parameter
     *
     * @param id Identity number of the employee
     * @return employee entity
     */
    @Override
    public Employee getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Employee.class, "id", id.toString()));
    }

    /**
     * Method gets information about all employees from Repository
     *
     * @return List of all employees
     */
    @Override
    public List<Employee> getAll() {
        log.info("In getAll()");
        return employeeRepository.getAll();
    }

    /**
     * Method updates information for an existing employee in  Repository
     *
     * @param object Employee entity with info to be updated
     * @return updated Employee entity
     */
    @Override
    public Employee update(Employee object) {
        log.info("In update(entity = [{}]", object);
        if (isEmployeeExistsWithEmail(object.getEmail())) {
            return employeeRepository.update(object);
        } else {
            throw new EntityNotFoundException(Employee.class, "email", object.getEmail());
        }
    }

    /**
     * Method saves a new employee to Repository
     *
     * @param object Employee entity with info to be saved
     * @return saved Employee entity
     */
    @Override
    public Employee save(Employee object) {
        log.info("In save(entity = [{}]", object);
        if (isEmployeeExistsWithEmail(object.getEmail())) {
            throw new FieldAlreadyExistsException(Employee.class, "email", object.getEmail());
        }
        return employeeRepository.save(object);
    }

    /**
     * Method deletes an existing employee from Repository
     *
     * @param object Employee entity to be deleted
     * @return deleted Employee entity
     */
    @Override
    public Employee delete(Employee object) {
        log.info("In delete(entity = [{}])", object);
        return employeeRepository.delete(object);
    }

    /**
     * Method finds if Employee with title already exists
     *
     * @param email
     * @return true if Employee with such title already exist
     */
    @Override
    public boolean isEmployeeExistsWithEmail(String email) {
        log.info("In isEmployeeExistsWithEmail(email = [{}])", email);
        return employeeRepository.countEmployeeWithEmail(email) != 0;
    }

    /**
     * Method finds number of Employees by boss email
     *
     * @param email
     * @return number of Employees with such boss email already exist
     */
    @Override
    public Long getNumberOfEmployeesByBossEmail(String email) {
        log.info("In getCountOfEmployeesByBossEmail(email = [{}])", email);
        return employeeRepository.countEmployeesByBossEmail(email);
    }

    /**
     * Method gets information about all employees by boss email from Repository
     *
     * @return List of employees
     */
    @Override
    public List<Employee> getEmployeesByBossEmail(String email) {
        log.info("In getEmployeesByBossEmail(email = [{}])", email);
        return employeeRepository.findEmployeesByBossEmail(email).orElse(Collections.emptyList());
    }

    /**
     * Method gets information about all employees by boss email from Repository
     *
     * @return List of employees
     */
    @Override
    public List<Employee> performEmployeesBranch(String email) {
        log.info("In performEmployeesBranch(email = [{}])", email);
        List<Employee> employees = getEmployeesByBossEmail(email);
        for(Employee e : employees){
            e.getPosition().setNumberOfSubordinate(getNumberOfEmployeesByBossEmail(e.getEmail()));
        }
        return employees;
    }

    /**
     * Method gets information about boss from Repository
     *
     * @return Head Employee
     */
    @Override
    public Employee getBoss(UUID id) {
        log.info("In getBoss(id = [{}])", id);
        return employeeRepository.findBoss(id).orElseThrow(
                () -> new EntityNotFoundException(Employee.class, "id", id.toString()));
    }

}
