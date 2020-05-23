package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.exception.FieldAlreadyExistsException;
import com.course.project.businessmanager.repository.BuildingRepository;
import com.course.project.businessmanager.service.BuildingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }


    /**
     * Method gets information from Repository for particular иuilding with id parameter
     *
     * @param id Identity number of the building
     * @return building entity
     */
    @Override
    public Building getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return buildingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Building.class, "id", id.toString()));
    }

    /**
     * Method gets information about all buildings from Repository
     *
     * @return List of all buildings
     */
    @Override
    public List<Building> getAll() {
        log.info("In getAll()");
        return buildingRepository.getAll();
    }

    /**
     * Method saves a new building to Repository
     *
     * @param object Building entity with info to be saved
     * @return saved Building entity
     */
    @Override
    public Building save(Building object) {
        log.info("In save(entity = [{}]", object);
        if (isBuildingExistsWithTitle(object.getName())) {
            throw new FieldAlreadyExistsException(Building.class, "title", object.getName());
        }
        return buildingRepository.save(object);
    }

    /**
     * Method updates information for an existing building in  Repository
     *
     * @param object Building entity with info to be updated
     * @return updated Building entity
     */
    @Override
    public Building update(Building object) {
        log.info("In update(entity = [{}]", object);
        if (isBuildingExistsWithTitle(object.getName())) {
            return buildingRepository.update(object);
        } else {
            throw new EntityNotFoundException(Building.class, "id", String.valueOf(object.getId()));
        }
    }

    /**
     * Method deletes an existing building from Repository
     *
     * @param object Building entity to be deleted
     * @return deleted Building entity
     */
    @Override
    public Building delete(Building object) {
        log.info("In delete(entity = [{}])", object);
        return buildingRepository.delete(object);
    }

    /**
     * Method finds if Building with title already exists
     *
     * @param title
     * @return true if Business with such title already exist
     */
    @Override
    public boolean isBuildingExistsWithTitle(String title) {
        log.info("In isBusinessExistsWithTitle(title = [{}])", title);
        return buildingRepository.countBuildingWithName(title) != 0;
    }
}
