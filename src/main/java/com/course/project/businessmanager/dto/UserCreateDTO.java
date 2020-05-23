package com.course.project.businessmanager.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class UserCreateDTO {

    private UUID id;
    private String email;
    private String password;
}
