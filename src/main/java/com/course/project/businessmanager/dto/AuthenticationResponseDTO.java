package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {
    @Email
    @Size(min = 5, max = 40)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    private Role role;

    private String token;

}


