package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Business;
import com.course.project.businessmanager.entity.Note;
import com.course.project.businessmanager.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserDTO {
    private UUID id;

    @Email
    @Size(min = 5, max = 40)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @JsonIgnore
    private String password;

    private Role role;

    List<Business> businessList = new ArrayList<>();

    private List<Note> notes = new ArrayList<>();
}

