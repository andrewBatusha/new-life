package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.PositionDTO;
import com.course.project.businessmanager.entity.Position;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    Position convertToEntity(PositionDTO dto);

    PositionDTO convertToDto(Position entity);

    List<PositionDTO> convertToDtoList(List<Position> notes);
}
