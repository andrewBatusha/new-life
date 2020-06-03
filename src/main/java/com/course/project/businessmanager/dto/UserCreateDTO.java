package com.course.project.businessmanager.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserCreateDTO {

    private UUID id;

    @Email
    @Size(min = 5, max = 40)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "password must not be blank")
    private String password;
}
