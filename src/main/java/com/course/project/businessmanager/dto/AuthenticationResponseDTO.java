package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private String email;
    private Role role;
    private String token;

}


