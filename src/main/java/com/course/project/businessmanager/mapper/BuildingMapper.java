package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.AddManagerToBuildingDTO;
import com.course.project.businessmanager.dto.BuildingDTO;
import com.course.project.businessmanager.entity.Building;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    BuildingDTO convertToDto(Building building);

    Building convertToEntity(BuildingDTO buildingDTO);

    Building convertToEntity(AddManagerToBuildingDTO addManagerToBuildingDTO);

    List<BuildingDTO> convertToDtoList(List<Building> buildings);

}
