package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Employee;
import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.entity.Warehouse;
import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.exception.BookKeepingException;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.exception.EntityWithQuantityException;
import com.course.project.businessmanager.repository.EquipmentRepository;
import com.course.project.businessmanager.service.EmployeeService;
import com.course.project.businessmanager.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EmployeeService employeeService;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EmployeeService employeeService) {
        this.equipmentRepository = equipmentRepository;
        this.employeeService = employeeService;
    }

    /**
     * Method gets information from Repository for particular equipment with id parameter
     *
     * @param id Identity number of the Equipment
     * @return Equipment entity
     */
    @Override
    public Equipment getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return equipmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Equipment.class, "id", id.toString()));
    }

    /**
     * Method saves new equipment to Repository
     *
     * @param object Equipment entity with info to be saved
     * @return saved Equipment entity or updated entity
     */
    @Override
    public Equipment save(Equipment object, Bookkeeping bookkeeping) {
        log.info("In save(entity = [{}]", object);
        if (isEquipmentExistsWithTitle(object.getName())) {
            return update(object, bookkeeping);
        } else if (bookkeeping == Bookkeeping.EXPENSES) {
            return equipmentRepository.save(object);
        } else {
            throw new BookKeepingException("There is no such item in equipment");
        }
    }

    /**
     * Method updates information for an existing equipment in  Repository
     *
     * @param object Equipment entity with info to be updated
     * @return updated Equipment entity
     */
    @Override
    public Equipment update(Equipment object, Bookkeeping bookkeeping) {
        log.info("In update(entity = [{}]", object);
        Equipment equipment = getEquipmentByName(object.getName(), object.getBuilding().getName());
        if (bookkeeping == Bookkeeping.EXPENSES) {
            equipment.setQuantity(equipment.getQuantity() + object.getQuantity());
            equipment = equipmentRepository.update(equipment);
        } else if (bookkeeping == Bookkeeping.INCOME) {
            if (equipment.getQuantity() < object.getQuantity()) {
                throw new EntityWithQuantityException("fuck of");
            } else if (equipment.getQuantity().equals(object.getQuantity())) {
                equipment = delete(equipment);
            } else {
                equipment.setQuantity(equipment.getQuantity() - object.getQuantity());
                equipment = equipmentRepository.update(equipment);
            }
        }
        return equipment;
    }

    /**
     * Method deletes an existing equipment from Repository
     *
     * @param object Equipment entity to be deleted
     * @return deleted Equipment entity
     */
    @Override
    public Equipment delete(Equipment object) {
        log.info("In delete(entity = [{}])", object);
        return equipmentRepository.delete(object);
    }

    /**
     * Method finds if Equipment with title already exists
     *
     * @param title
     * @return true if Equipment with such title already exist
     */
    @Override
    public boolean isEquipmentExistsWithTitle(String title) {
        log.info("In isEquipmentExistsWithTitle(title = [{}])", title);
        return equipmentRepository.countEquipmentsWithName(title) != 0;
    }

    /**
     * Method finds Equipment by name
     *
     * @param title
     * @param buildingName
     * @return equipment
     */
    @Override
    public Equipment getEquipmentByName(String title, String buildingName) {
        log.info("In isWarehouseExistsWithTitle(title = [{}], buildingName = [{}])", title, buildingName);
        return equipmentRepository.findByName(title, buildingName).orElseThrow(
                () -> new EntityNotFoundException(Warehouse.class, "title", title)
        );
    }

    @Override
    public void assignEmployee(Equipment equipmentToUpdate, String email){
        log.info("In assignEmployee(equipmentToUpdate = [{}], email = [{}])", equipmentToUpdate, email);
        Employee employee = employeeService.getEmployeeByEmail(email, equipmentToUpdate.getBuilding().getName());
        equipmentToUpdate.setQuantity(equipmentToUpdate.getQuantity()-1);
        Equipment equipment = Equipment.builder().name(equipmentToUpdate.getName()).price(equipmentToUpdate.getPrice()).quantity(1L).building(equipmentToUpdate.getBuilding()).build();
        equipment.setEmployee(employee);
        equipmentRepository.save(equipment);
        equipmentRepository.update(equipmentToUpdate);
    }

}
