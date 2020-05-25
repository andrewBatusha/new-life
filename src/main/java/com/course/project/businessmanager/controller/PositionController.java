package com.course.project.businessmanager.controller;

import com.course.project.businessmanager.dto.PositionDTO;
import com.course.project.businessmanager.entity.Position;
import com.course.project.businessmanager.mapper.PositionMapper;
import com.course.project.businessmanager.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Api(tags = "Position API")
@RequestMapping("/positions")
@Slf4j
public class PositionController {

    private final PositionService positionService;
    private final PositionMapper positionMapper;

    @Autowired
    public PositionController(PositionService positionService, PositionMapper positionMapper) {
        this.positionService = positionService;
        this.positionMapper = positionMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new position")
    public ResponseEntity<PositionDTO> save(@Valid @RequestBody PositionDTO positionDTO) {
        log.info("Enter into save of PositionController with positionDTO: {}", positionDTO);
        Position position = positionService.save(positionMapper.convertToEntity(positionDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(positionMapper.convertToDto(position));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get position info by id")
    public ResponseEntity<PositionDTO> get(@PathVariable("id") UUID id){
        log.info("In get(id = [{}])", id);
        Position position = positionService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(positionMapper.convertToDto(position));
    }

    @PutMapping
    @ApiOperation(value = "Update existing position")
    public ResponseEntity<PositionDTO> update(@Valid @RequestBody PositionDTO positionDTO) {
        log.info("In update (positionDTO = [{}])", positionDTO);
        Position position = positionService.update(positionMapper.convertToEntity(positionDTO));
        return ResponseEntity.status(HttpStatus.OK).body(positionMapper.convertToDto(position));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete employee by id")
    public ResponseEntity delete(@PathVariable("id") UUID id){
        log.info("In delete (id =[{}]", id);
        Position position = positionService.getById(id);
        positionService.delete(position);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
