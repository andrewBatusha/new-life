package com.course.project.businessmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class RegistrationRequestDTO {
    @Email
    private String email;
    private String password;

}

