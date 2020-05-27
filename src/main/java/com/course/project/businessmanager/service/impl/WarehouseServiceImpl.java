package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Warehouse;
import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.exception.WarehouseQuantityException;
import com.course.project.businessmanager.repository.WarehouseRepository;
import com.course.project.businessmanager.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }


    /**
     * Method gets information from Repository for particular warehouse with id parameter
     *
     * @param id Identity number of the warehouse
     * @return Warehouse entity
     */
    @Override
    public Warehouse getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return warehouseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Warehouse.class, "id", id.toString()));
    }

    /**
     * Method gets information about all warehouses from Repository
     *
     * @return List of all warehouses
     */
    @Override
    public List<Warehouse> getAll() {
        log.info("In getAll()");
        return warehouseRepository.getAll();
    }

    /**
     * Method saves new warehouse to Repository
     *
     * @param object Warehouse entity with info to be saved
     * @return saved Warehouse entity or updated entity
     */
    @Override
    public Warehouse save(Warehouse object, Bookkeeping bookkeeping) {
        log.info("In save(entity = [{}]", object);
        if (isWarehouseExistsWithTitle(object.getName())) {
           return update(object, bookkeeping);
        }
        return warehouseRepository.save(object);
    }

    /**
     * Method updates information for an existing warehouse in  Repository
     *
     * @param object Warehouse entity with info to be updated
     * @return updated Warehouse entity
     */
    @Override
    public Warehouse update(Warehouse object, Bookkeeping bookkeeping) {
        log.info("In update(entity = [{}]", object);
        Warehouse dbWarehouse = getWarehouseByName(object.getName());
        if (bookkeeping == Bookkeeping.EXPENSES) {
            dbWarehouse.setQuantity(dbWarehouse.getQuantity() + object.getQuantity());
            dbWarehouse = warehouseRepository.update(dbWarehouse);
        } else if (bookkeeping == Bookkeeping.INCOME) {
            if (dbWarehouse.getQuantity() < object.getQuantity()) {
                throw new WarehouseQuantityException("fuck of");
            } else if (dbWarehouse.getQuantity().equals(object.getQuantity())) {
                dbWarehouse = delete(dbWarehouse);
            } else {
                dbWarehouse.setQuantity(dbWarehouse.getQuantity() - object.getQuantity());
                dbWarehouse = warehouseRepository.update(dbWarehouse);
            }
        }
        return dbWarehouse;
    }

    /**
     * Method deletes an existing warehouse from Repository
     *
     * @param object Warehouse entity to be deleted
     * @return deleted Warehouse entity
     */
    @Override
    public Warehouse delete(Warehouse object) {
        log.info("In delete(entity = [{}])", object);
        return warehouseRepository.delete(object);
    }

    /**
     * Method finds if Warehouse with title already exists
     *
     * @param title
     * @return true if Warehouse with such title already exist
     */
    @Override
    public boolean isWarehouseExistsWithTitle(String title) {
        log.info("In isWarehouseExistsWithTitle(title = [{}])", title);
        return warehouseRepository.countWarehouseWithName(title) != 0;
    }

    /**
     * Method finds Warehouse by name
     *
     * @param title
     * @return warehouse
     */
    @Override
    public Warehouse getWarehouseByName(String title) {
        log.info("In isWarehouseExistsWithTitle(title = [{}])", title);
        return warehouseRepository.findByName(title).orElseThrow(
                () -> new EntityNotFoundException(Warehouse.class, "title", title)
        );
    }
}
