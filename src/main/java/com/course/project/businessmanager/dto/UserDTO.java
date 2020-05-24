package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Business;
import com.course.project.businessmanager.entity.Note;
import com.course.project.businessmanager.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserDTO {
    private UUID id;

    private String email;

    @JsonIgnore
    private String password;

    private Role role;

    List<Business> businessList = new ArrayList<>();

    private List<Note> notes = new ArrayList<>();
}

