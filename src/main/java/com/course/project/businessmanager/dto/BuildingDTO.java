package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Business;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.UUID;

@Data
@JsonIgnoreProperties(value = { "business" })
public class BuildingDTO {
    private UUID id;

    private String name;

    private String geolocation;

    private Business business;
}
