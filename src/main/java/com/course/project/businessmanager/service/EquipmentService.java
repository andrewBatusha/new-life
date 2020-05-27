package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.entity.enums.Bookkeeping;

import java.util.UUID;

public interface EquipmentService{
    public boolean isEquipmentExistsWithTitle(String title);
    Equipment save(Equipment object, Bookkeeping bookkeeping);
    Equipment update(Equipment object, Bookkeeping bookkeeping);
    Equipment getEquipmentByName(String title, String buildingName);
    Equipment delete(Equipment object);
    Equipment getById(UUID id);
    void assignEmployee(Equipment equipmentToUpdate, String email);
}
