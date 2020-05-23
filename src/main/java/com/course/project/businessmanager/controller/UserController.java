package com.course.project.businessmanager.controller;

import com.course.project.businessmanager.dto.BusinessDTO;
import com.course.project.businessmanager.dto.UserCreateDTO;
import com.course.project.businessmanager.dto.UserDTO;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.mapper.BusinessMapper;
import com.course.project.businessmanager.mapper.UserMapper;
import com.course.project.businessmanager.security.jwt.JwtTokenProvider;
import com.course.project.businessmanager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
@Api(tags = "User")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final BusinessMapper businessMapper;

    @GetMapping
    @ApiOperation(value = "Get the list of all users")
    public ResponseEntity<List<UserDTO>> getAll() {
        log.info("Enter into getAll method");
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserDTOs(userService.getAll()));
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id")
    public ResponseEntity<UserDTO> get(@PathVariable("id") UUID id) {
        log.info("Enter into get method with id: {} ", id);
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserDTO(user));
    }

    @GetMapping("/token")
    @ApiOperation(value = "Get user by token in header")
    public ResponseEntity<List<BusinessDTO>> getByToken(HttpServletRequest req) {
        log.info("Enter into get method with by header ");
        String token = jwtTokenProvider.resolveToken(req);
        String email = jwtTokenProvider.getUsername(token);
        User user = userService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(businessMapper.toBusinessDTOs(user.getBusinessList()));
    }

    @PostMapping
    @ApiOperation(value = "Create new user")
    public ResponseEntity<UserCreateDTO> save(@RequestBody UserCreateDTO createUserDTO) {
        log.info("Enter into save method with createUserDTO: {}", createUserDTO);
        User user = userService.save(userMapper.toUser(createUserDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserCreateDTO(user));
    }


    @PutMapping
    @ApiOperation(value = "Update existing user by id")
    public ResponseEntity<UserCreateDTO> update(@RequestBody UserCreateDTO userDTO) {
        log.info("Enter into update method with userDTO: {}", userDTO);
        User updatedUser = userMapper.toUser(userDTO);
        userService.update(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toUserCreateDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by id")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {

        log.info("Enter into delete method with group id: {}", id);
        User user = userService.getById(id);
        userService.delete(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

