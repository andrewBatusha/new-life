package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Warehouse;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository extends BasicRepository<Warehouse, UUID> {
    Long countWarehouseWithName(String title);

    Optional<Warehouse> findByName(String warehouseName, String buildingName);

}
