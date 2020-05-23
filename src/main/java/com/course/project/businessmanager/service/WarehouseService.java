package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Warehouse;
import com.course.project.businessmanager.entity.enums.Bookkeeping;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {
    Warehouse getById(UUID id);

    List<Warehouse> getAll();

    Warehouse save(Warehouse object, Bookkeeping bookkeeping);

    boolean isWarehouseExistsWithTitle(String title);

    Warehouse getWarehouseByName(String title);

    void update(Warehouse object, Bookkeeping bookkeeping);

    Warehouse delete(Warehouse object);
}
