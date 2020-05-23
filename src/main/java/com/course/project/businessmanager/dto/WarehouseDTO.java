package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class WarehouseDTO {

    private UUID id;

    private String name;

    private int quantity;

    private String unitOfMeasurement;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;
}

