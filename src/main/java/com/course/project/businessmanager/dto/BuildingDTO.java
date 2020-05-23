package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Business;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class BuildingDTO {
    private UUID id;

    private String name;

    private String geolocation;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Business business;
}
