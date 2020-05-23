package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class EquipmentDTO {
    private UUID id;

    private String name;

    private int quantity;

    private int price;

    private Employee employee;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;
}
