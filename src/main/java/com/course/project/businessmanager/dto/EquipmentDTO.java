package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.Employee;
import lombok.Data;

import java.util.UUID;

@Data
public class EquipmentDTO {
    private UUID id;

    private String name;

    private Employee employee;

    private Building building;
}
