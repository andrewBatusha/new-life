package com.course.project.businessmanager.controller;

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

import java.util.List;
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


    @GetMapping
    @ApiOperation(value = "Get the list of all equipments")
    public ResponseEntity<List<EquipmentDTO>> list() {
        log.info("Enter into list of EquipmentController");
        return ResponseEntity.ok().body(equipmentMapper.convertToDtoList(equipmentService.getAll()));
    }

    @PutMapping
    @ApiOperation(value = "Update existing equipment by id")
    public ResponseEntity<EquipmentDTO> update(@RequestBody EquipmentDTO equipmentDTO) {
        log.info("In update (EquipmentDTO = [{}])", equipmentDTO);
        Equipment equipment = equipmentService.update(equipmentMapper.convertToEntity(equipmentDTO));
        return ResponseEntity.status(HttpStatus.OK).body(equipmentMapper.convertToDto(equipment));
    }
}
