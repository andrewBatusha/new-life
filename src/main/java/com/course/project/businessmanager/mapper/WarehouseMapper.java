package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.WarehouseDTO;
import com.course.project.businessmanager.entity.Warehouse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse convertToEntity(WarehouseDTO dto);

    WarehouseDTO convertToDto(Warehouse entity);

    List<WarehouseDTO> convertToDtoList(List<Warehouse> rooms);

}
