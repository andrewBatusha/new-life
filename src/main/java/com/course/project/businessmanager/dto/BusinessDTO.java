package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BusinessDTO {
    private UUID id;

    private String name;

    private List<User> users = new ArrayList<>();

    private List<Building> buildings = new ArrayList<>();
}
