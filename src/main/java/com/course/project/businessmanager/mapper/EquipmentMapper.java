package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.AssignEmployeeToEquipmentDTO;
import com.course.project.businessmanager.dto.EquipmentDTO;
import com.course.project.businessmanager.entity.Equipment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    Equipment convertToEntity(EquipmentDTO dto);

    Equipment convertToEntity(AssignEmployeeToEquipmentDTO dto);

    EquipmentDTO convertToDto(Equipment entity);

    List<EquipmentDTO> convertToDtoList(List<Equipment> equipmentList);

}