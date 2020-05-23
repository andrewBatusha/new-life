package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.repository.EquipmentRepository;
import com.course.project.businessmanager.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
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
     * Method gets information about all equipments from Repository
     *
     * @return List of all equipments
     */
    @Override
    public List<Equipment> getAll() {
        log.info("In getAll()");
        return equipmentRepository.getAll();
    }

    /**
     * Method saves new equipment to Repository
     *
     * @param object Equipment entity with info to be saved
     * @return saved Equipment entity or updated entity
     */
    @Override
    public Equipment save(Equipment object) {
        log.info("In save(entity = [{}]", object);
        if (isEquipmentExistsWithTitle(object.getName())) {
            return update(object);
        }
        return equipmentRepository.save(object);
    }

    /**
     * Method updates information for an existing equipment in  Repository
     *
     * @param object Equipment entity with info to be updated
     * @return updated Equipment entity
     */
    @Override
    public Equipment update(Equipment object) {
        log.info("In update(entity = [{}]", object);
        if (isEquipmentExistsWithTitle(object.getName())) {
            return equipmentRepository.update(object);
        } else {
            throw new EntityNotFoundException(Equipment.class, "id", String.valueOf(object.getId()));
        }
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


}
