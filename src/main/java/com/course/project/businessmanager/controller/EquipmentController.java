package com.course.project.businessmanager.controller;

import com.course.project.businessmanager.dto.AssignEmployeeToEquipmentDTO;
import com.course.project.businessmanager.dto.EquipmentDTO;
import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.mapper.EquipmentMapper;
import com.course.project.businessmanager.service.EquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Api(tags = "Equipment API")
@RequestMapping("/equipments")
@Slf4j
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    @Autowired
    public EquipmentController(EquipmentService equipmentService, EquipmentMapper equipmentMapper) {
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get equipment info by id")
    public ResponseEntity<EquipmentDTO> get(@PathVariable("id") UUID id){
        log.info("In get(id = [{}])", id);
        Equipment equipment = equipmentService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipmentMapper.convertToDto(equipment));
    }

    @PutMapping("/employee")
    @ApiOperation(value = "Get equipment info by id")
    public ResponseEntity assign(@Valid @RequestBody AssignEmployeeToEquipmentDTO assignEmployeeToEquipmentDTO){
        log.info("In assign(assignEmployeeToEquipmentDTO = [{}])", assignEmployeeToEquipmentDTO);
        Equipment equipmentToUpdate = equipmentMapper.convertToEntity(assignEmployeeToEquipmentDTO);
        equipmentService.assignEmployee(equipmentToUpdate, assignEmployeeToEquipmentDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
