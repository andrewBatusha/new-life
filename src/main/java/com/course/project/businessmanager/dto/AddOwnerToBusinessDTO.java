package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AddOwnerToBusinessDTO {
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    private String name;

    private List<User> users = new ArrayList<>();

    private List<Building> buildings = new ArrayList<>();

    private String email;
}
