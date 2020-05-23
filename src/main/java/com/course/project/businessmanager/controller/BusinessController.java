package com.course.project.businessmanager.controller;

import com.course.project.businessmanager.dto.AddUserToBusinessDTO;
import com.course.project.businessmanager.dto.BusinessDTO;
import com.course.project.businessmanager.entity.Business;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.mapper.BusinessMapper;
import com.course.project.businessmanager.security.jwt.JwtTokenProvider;
import com.course.project.businessmanager.service.BusinessService;
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
@Api(tags = "Business API")
@RequestMapping("/businesses")
@Slf4j
public class BusinessController {

    private final BusinessService businessService;
    private final BusinessMapper businessMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public BusinessController(BusinessService businessService, BusinessMapper businessMapper, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.businessService = businessService;
        this.businessMapper = businessMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Get the list of all businesses")
    public ResponseEntity<List<BusinessDTO>> list() {
        log.info("Enter into list of BusinessController");
        return ResponseEntity.ok().body(businessMapper.toBusinessDTOs(businessService.getAll()));
    }


    @PostMapping
    @ApiOperation(value = "Create new business")
    public ResponseEntity<BusinessDTO> save(@RequestBody BusinessDTO businessDTO, HttpServletRequest req) {
        log.info("Enter into save of BusinessController with businessDTO: {}", businessDTO);
        String token = jwtTokenProvider.resolveToken(req);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByEmail(username);
        Business business = businessMapper.toBusiness(businessDTO);
        business.getUsers().add(user);
        Business newBusiness = businessService.save(business);
        return ResponseEntity.status(HttpStatus.CREATED).body(businessMapper.toBusinessDTO(newBusiness));
    }

    @PutMapping
    @ApiOperation(value = "Update existing business by id")
    public ResponseEntity<BusinessDTO> update(@RequestBody BusinessDTO businessDTO) {
        log.info("In update (businessDTO = [{}])", businessDTO);
        Business business = businessService.update(businessMapper.toBusiness(businessDTO));
        return ResponseEntity.status(HttpStatus.OK).body(businessMapper.toBusinessDTO(business));
    }

    @PutMapping("/user")
    @ApiOperation(value = "Add user to existing business")
    public ResponseEntity<BusinessDTO> addUserToBusiness(@RequestBody AddUserToBusinessDTO addUserToBusinessDTO) {
        log.info("In update (addUserToBusinessDTO = [{}])", addUserToBusinessDTO);
        Business updatedBusiness = businessService.addUserToBusiness(
                businessMapper.toBusiness(addUserToBusinessDTO),
                addUserToBusinessDTO.getEmail()
        );
        Business business = businessService.update(updatedBusiness);
        return ResponseEntity.status(HttpStatus.OK).body(businessMapper.toBusinessDTO(business));
    }



    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete business by id")
    public ResponseEntity delete(@PathVariable("id") UUID id) {
        log.info("In delete (id =[{}]", id);
        Business business = businessService.getById(id);
        businessService.delete(business);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
