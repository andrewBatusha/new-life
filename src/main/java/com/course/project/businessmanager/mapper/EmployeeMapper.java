package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.EmployeeDTO;
import com.course.project.businessmanager.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee convertToEntity(EmployeeDTO dto);

    EmployeeDTO convertToDto(Employee entity);

    List<EmployeeDTO> convertToDtoList(List<Employee> equipmentList);
}
