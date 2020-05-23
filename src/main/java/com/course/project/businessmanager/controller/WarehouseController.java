package com.course.project.businessmanager.controller;

import com.course.project.businessmanager.dto.WarehouseDTO;
import com.course.project.businessmanager.entity.Warehouse;
import com.course.project.businessmanager.mapper.WarehouseMapper;
import com.course.project.businessmanager.service.WarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "Warehouse API")
@RequestMapping("/warehouses")
@Slf4j
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final WarehouseMapper warehouseMapper;

    @Autowired
    public WarehouseController(WarehouseService warehouseService, WarehouseMapper warehouseMapper) {
        this.warehouseService = warehouseService;
        this.warehouseMapper = warehouseMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get warehouse info by id")
    public ResponseEntity<WarehouseDTO> get(@PathVariable("id") UUID id){
        log.info("In get(id = [{}])", id);
        Warehouse warehouse = warehouseService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(warehouseMapper.convertToDto(warehouse));
    }


    @GetMapping
    @ApiOperation(value = "Get the list of all warehouses")
    public ResponseEntity<List<WarehouseDTO>> list() {
        log.info("Enter into list of WarehouseController");
        return ResponseEntity.ok().body(warehouseMapper.convertToDtoList(warehouseService.getAll()));
    }

}
