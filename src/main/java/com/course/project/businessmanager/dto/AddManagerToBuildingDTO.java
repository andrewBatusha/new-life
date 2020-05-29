package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Business;
import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AddManagerToBuildingDTO {
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    private String name;

    private String geolocation;

    private List<Equipment> equipments = new ArrayList<>();

    private List<Ledger> ledgers = new ArrayList<>();

    private List<Warehouse> warehouses = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Business business;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User user;

    @Email
    @Size(min = 5, max = 40)
    @NotBlank(message = "Email cannot be empty")
    private String email;
}
