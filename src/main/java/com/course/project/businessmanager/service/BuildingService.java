package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Building;

import java.util.UUID;

public interface BuildingService extends BasicService<Building, UUID>{
    boolean isBuildingExistsWithTitle(String title);
}
