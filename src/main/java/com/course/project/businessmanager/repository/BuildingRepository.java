package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Building;

import java.util.UUID;

public interface BuildingRepository extends BasicRepository<Building, UUID> {
    public Long countBuildingWithName(String name);
}
