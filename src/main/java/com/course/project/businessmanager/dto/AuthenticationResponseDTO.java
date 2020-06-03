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
    @Email(message = "input email must be a well-formed")
    @Size(min = 5, max = 40, message = "Email must be between 2 and 35 characters long")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    private Role role;

    private String token;

}


