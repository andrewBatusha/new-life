package com.course.project.businessmanager.mapper;


import com.course.project.businessmanager.dto.AddOwnerToBusinessDTO;
import com.course.project.businessmanager.dto.BusinessDTO;
import com.course.project.businessmanager.entity.Business;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusinessMapper {
    BusinessDTO convertToDto(Business business);

    Business convertToEntity(BusinessDTO businessDTO);

    Business convertToEntity(AddOwnerToBusinessDTO addOwnerToBusinessDTO);

    List<BusinessDTO> convertToDtoList(List<Business> businessList);

}
