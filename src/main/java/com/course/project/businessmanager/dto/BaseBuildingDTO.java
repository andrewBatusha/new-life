package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.User;
import lombok.Data;

import java.util.UUID;

@Data
public class BaseBuildingDTO {

    private UUID id;

    private User user;
}
