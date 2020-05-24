package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Building;

import java.util.Optional;
import java.util.UUID;

public interface BuildingRepository extends BasicRepository<Building, UUID> {
    Long countBuildingWithName(String name);

    Optional<Building> findBuildingByEmail(String email);
}
