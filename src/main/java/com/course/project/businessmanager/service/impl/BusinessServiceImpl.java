package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Business;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.entity.enums.Role;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.exception.FieldAlreadyExistsException;
import com.course.project.businessmanager.repository.BusinessRepository;
import com.course.project.businessmanager.security.jwt.JwtTokenProvider;
import com.course.project.businessmanager.service.BusinessService;
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
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final UserService userService;

    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.businessRepository = businessRepository;
        this.userService = userService;
    }



    /**
     * Method gets information from Repository for particular business with id parameter
     *
     * @param id Identity number of the business
     * @return Business entity
     */
    @Override
    public Business getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return businessRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Business.class, "id", id.toString()));
    }

    /**
     * Method gets information about all business from Repository
     *
     * @return List of all businesses
     */
    @Override
    public List<Business> getAll() {
        log.info("In getAll()");
        return businessRepository.getAll();
    }

    @Override
    public Business addUserToBusiness(Business object, String email){
        log.info("In addUserToBusiness(entity = [{}], email = [{}]", object, email);
        User user = userService.findByEmail(email);
        user.setRole(Role.ROLE_OWNER);
        object.getUsers().add(user);
        return object;
    }

    /**
     * Method saves a new business to Repository
     *
     * @param object Business entity with info to be saved
     * @return saved Business entity
     */
    @Override
    public Business save(Business object) {
        log.info("In save(entity = [{}]", object);
        return businessRepository.save(object);
    }

    /**
     * Method updates information for an existing business in  Repository
     *
     * @param object Business entity with info to be updated
     * @return updated Business entity
     */
    @Override
    public Business update(Business object) {
        log.info("In update(entity = [{}]", object);
        if (isBusinessExistsWithId(object.getId())) {
            return businessRepository.update(object);
        } else {
            throw new EntityNotFoundException(Business.class, "id", String.valueOf(object.getId()));
        }
    }

    /**
     * Method deletes an existing business from Repository
     *
     * @param object Business entity to be deleted
     * @return deleted Business entity
     */
    @Override
    public Business delete(Business object) {
        log.info("In delete(entity = [{}])", object);
        return businessRepository.delete(object);
    }

    /**
     * Method finds if Business with title already exists
     *
     * @param uuid
     * @return true if Business with such title already exist
     */
    @Override
    public boolean isBusinessExistsWithId(UUID uuid) {
        log.info("In isBusinessExistsWithId(uuid = [{}])", uuid);
        return businessRepository.countBusinessWithId(uuid) != 0;
    }

}