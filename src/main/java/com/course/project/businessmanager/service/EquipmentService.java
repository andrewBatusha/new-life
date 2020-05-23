package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Equipment;

import java.util.UUID;

public interface EquipmentService extends BasicService<Equipment, UUID>{
    public boolean isEquipmentExistsWithTitle(String title);
}
