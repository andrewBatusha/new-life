package com.course.project.businessmanager.controller;


import com.course.project.businessmanager.dto.AddManagerToBuildingDTO;
import com.course.project.businessmanager.dto.BuildingDTO;
import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.mapper.BuildingMapper;
import com.course.project.businessmanager.security.jwt.JwtTokenProvider;
import com.course.project.businessmanager.service.BuildingService;
import com.course.project.businessmanager.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "Building API")
@RequestMapping("/buildings")
@Slf4j
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingMapper buildingMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public BuildingController(BuildingService buildingService, BuildingMapper buildingMapper, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.buildingService = buildingService;
        this.buildingMapper = buildingMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Get the list of all buildings")
    public ResponseEntity<List<BuildingDTO>> list() {
        log.info("Enter into list of BuildingController");
        return ResponseEntity.ok().body(buildingMapper.convertToDtoList(buildingService.getAll()));
    }

    @PutMapping("/user")
    @ApiOperation(value = "Add user to existing building")
    public ResponseEntity<BuildingDTO> addUserToBuilding(@RequestBody AddManagerToBuildingDTO addManagerToBuildingDTO) {
        log.info("In addUserToBusiness (addManagerToBuildingDTO = [{}])", addManagerToBuildingDTO);
        Building updatedBuilding = buildingService.addUserToBuilding(
                buildingMapper.convertToEntity(addManagerToBuildingDTO),
                addManagerToBuildingDTO.getEmail()
        );
        Building building = buildingService.update(updatedBuilding);
        return ResponseEntity.status(HttpStatus.OK).body(buildingMapper.convertToDto(building));
    }

    @PostMapping
    @ApiOperation(value = "Create new building")
    public ResponseEntity<BuildingDTO> save(@RequestBody BuildingDTO buildingDTO, HttpServletRequest req) {
        log.info("Enter into save of BuildingController with buildingDTO: {}", buildingDTO);
        String token = jwtTokenProvider.resolveToken(req);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByEmail(username);
        Building building = buildingMapper.convertToEntity(buildingDTO);
        building.setUser(user);
        Building newBuilding = buildingService.save(building);
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingMapper.convertToDto(newBuilding));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get building info by id")
    public ResponseEntity<BuildingDTO> get(@PathVariable("id") UUID id){
        log.info("In get(id = [{}])", id);
        Building building = buildingService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(buildingMapper.convertToDto(building));
    }

    @PutMapping
    @ApiOperation(value = "Update existing building by id")
    public ResponseEntity<BuildingDTO> update(@RequestBody BuildingDTO buildingDTO) {
        log.info("In update (buildingDTO = [{}])", buildingDTO);
        Building building = buildingService.update(buildingMapper.convertToEntity(buildingDTO));
        return ResponseEntity.status(HttpStatus.OK).body(buildingMapper.convertToDto(building));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete building by id")
    public ResponseEntity delete(@PathVariable("id") UUID id){
        log.info("In delete (id =[{}]", id);
        Building building = buildingService.getById(id);
        buildingService.delete(building);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
