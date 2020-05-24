package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.repository.BuildingRepository;
import com.course.project.businessmanager.service.BuildingService;
import com.course.project.businessmanager.service.UserService;
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
    private final UserService userService;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository, UserService userService) {
        this.buildingRepository = buildingRepository;
        this.userService = userService;
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
     * The method used for getting building id by email from database
     *
     * @param email String email used to find building uuid by it
     * @return UUID entity
     */
    @Override
    public Building findBuildingByEmail(String email) {
        log.info("Enter into findByToken method with email:{}", email);
        return buildingRepository.findBuildingByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(User.class, "email", email)
        );
    }

    @Override
    public Building addUserToBuilding(Building object, String email){
        log.info("In addUserToBusiness(entity = [{}], email = [{}]", object, email);
        User user = userService.createManager(email);
        object.setUser(user);
        return object;
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
