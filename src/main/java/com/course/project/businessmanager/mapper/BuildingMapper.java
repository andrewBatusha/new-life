package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.BuildingDTO;
import com.course.project.businessmanager.entity.Building;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    BuildingDTO toBuildingDTO(Building building);

    Building toBuilding(BuildingDTO buildingDTO);

    List<BuildingDTO> toBuildingsDTOs(List<Building> buildings);

}
