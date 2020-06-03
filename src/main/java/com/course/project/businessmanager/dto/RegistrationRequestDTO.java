package com.course.project.businessmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistrationRequestDTO {
    @Email(message = "input email must be a well-formed")
    @Size(min = 5, max = 40, message = "Email must be between 2 and 35 characters long")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "password must not be blank")
    private String password;

}

