package com.course.project.businessmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AuthenticationRequestDTO {
    @Email
    @Size(min = 5, max = 40)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank
    private String password;
}
