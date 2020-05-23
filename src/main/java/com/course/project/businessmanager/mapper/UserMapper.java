package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.RegistrationRequestDTO;
import com.course.project.businessmanager.dto.UserCreateDTO;
import com.course.project.businessmanager.dto.UserDTO;
import com.course.project.businessmanager.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);

    User toCreateUser(RegistrationRequestDTO registrationDTO);
    List<UserDTO> toUserDTOs(List<User> users);

    User toUser(UserCreateDTO userCreateDTO);
    UserCreateDTO toUserCreateDTO(User user);

}
