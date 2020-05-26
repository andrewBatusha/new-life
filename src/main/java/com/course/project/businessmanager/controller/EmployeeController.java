package com.course.project.businessmanager.controller;

import com.course.project.businessmanager.dto.BranchDTO;
import com.course.project.businessmanager.dto.BuildingDTO;
import com.course.project.businessmanager.dto.BusinessDTO;
import com.course.project.businessmanager.dto.EmployeeDTO;
import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.Employee;
import com.course.project.businessmanager.mapper.EmployeeMapper;
import com.course.project.businessmanager.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Api(tags = "Employee API")
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PutMapping("/branch")
    @ApiOperation(value = "Get the list of  employees by boss email")
    public ResponseEntity<BranchDTO> getEmployeesByBossEmail(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Enter into list of employees by boss email EmployeeController");
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setEmployees(employeeService.performEmployeesBranch(employeeDTO.getEmail()));
        employeeDTO.getPosition().setNumberOfSubordinate(employeeService.getNumberOfEmployeesByBossEmail(employeeDTO.getEmail()));
        branchDTO.setBoss(employeeMapper.convertToEntity(employeeDTO));
        return ResponseEntity.ok().body(branchDTO);
    }

    @PutMapping("/head")
    @ApiOperation(value = "Get the list of  employees by boss email")
    public ResponseEntity<EmployeeDTO> getBoss(@Valid @RequestBody BuildingDTO buildingDTO) {
        log.info("Enter into get boss of EmployeeController");
        Employee employee = employeeService.getBoss(buildingDTO.getId());
        return ResponseEntity.ok().body(employeeMapper.convertToDto(employee));
    }


    @PostMapping
    @ApiOperation(value = "Create new employee")
    public ResponseEntity<EmployeeDTO> save(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Enter into save of EmployeeController with employeeDTO: {}", employeeDTO);
        Employee employee = employeeService.save(employeeMapper.convertToEntity(employeeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.convertToDto(employee));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get employee info by id")
    public ResponseEntity<EmployeeDTO> get(@PathVariable("id") UUID id) {
        log.info("In get(id = [{}])", id);
        Employee employee = employeeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.convertToDto(employee));
    }

    @PutMapping
    @ApiOperation(value = "Update existing employee")
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("In update (employeeDTO = [{}])", employeeDTO);
        Employee employee = employeeService.update(employeeMapper.convertToEntity(employeeDTO));
        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.convertToDto(employee));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete employee by id")
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        log.info("In delete (id =[{}]", id);
        Employee employee = employeeService.getById(id);
        employeeService.delete(employee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
