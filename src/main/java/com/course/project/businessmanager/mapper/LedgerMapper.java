package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.LedgerDTO;
import com.course.project.businessmanager.entity.Ledger;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LedgerMapper {

    Ledger convertToEntity(LedgerDTO dto);

    LedgerDTO convertToDto(Ledger entity);

    List<LedgerDTO> convertToDtoList(List<Ledger> ledgers);

}
