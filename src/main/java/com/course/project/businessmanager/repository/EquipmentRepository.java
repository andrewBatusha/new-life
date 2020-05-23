package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Equipment;

import java.util.UUID;

public interface EquipmentRepository extends BasicRepository<Equipment, UUID> {
    Long countEquipmentsWithName(String title);
}
