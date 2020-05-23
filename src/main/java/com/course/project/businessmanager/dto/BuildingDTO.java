package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Business;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(value = { "business" })
public class BuildingDTO {
    private UUID id;

    private String name;

    private String geolocation;

    private Business business;
}
