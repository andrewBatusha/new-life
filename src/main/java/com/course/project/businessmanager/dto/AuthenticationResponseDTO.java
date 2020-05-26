package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {
    @Email
    @NotBlank
    private String email;

    private Role role;

    private String token;

}


