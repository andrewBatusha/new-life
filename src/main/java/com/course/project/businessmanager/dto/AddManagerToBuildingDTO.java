package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Business;
import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AddManagerToBuildingDTO {
    private UUID id;

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
    private String email;
}
