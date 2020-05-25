package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Position;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.repository.PositionRepository;
import com.course.project.businessmanager.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    /**
     * Method gets information from Repository for position with id parameter
     *
     * @param id Identity number of the position
     * @return Position entity
     */
    @Override
    public Position getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return positionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Position.class, "id", id.toString()));
    }

    /**
     * Method gets information about all positions from Repository
     *
     * @return List of all positions
     */
    @Override
    public List<Position> getAll() {
        log.info("In getAll()");
        return positionRepository.getAll();
    }

    /**
     * Method updates information for an existing position in  Repository
     *
     * @param object Position entity with info to be updated
     * @return updated Position entity
     */
    @Override
    public Position update(Position object) {
        log.info("In update(entity = [{}]", object);
        if (isPositionExistsWithUuid(object.getId().toString())) {
            return positionRepository.update(object);
        } else {
            throw new EntityNotFoundException(Position.class, "id", object.getId().toString());
        }
    }

    /**
     * Method saves a new position to Repository
     *
     * @param object Position entity with info to be saved
     * @return saved Position entity
     */
    @Override
    public Position save(Position object) {
        log.info("In save(entity = [{}]", object);
        return positionRepository.save(object);
    }

    /**
     * Method deletes an existing position from Repository
     *
     * @param object Position entity to be deleted
     * @return deleted Position entity
     */
    @Override
    public Position delete(Position object) {
        log.info("In delete(entity = [{}])", object);
        return positionRepository.delete(object);
    }

    /**
     * Method finds if Position with title already exists
     *
     * @param uuid
     * @return true if Position with such title already exist
     */
    @Override
    public boolean isPositionExistsWithUuid(String uuid) {
        log.info("In isPositionExistsWithUuid(uuid = [{}])", uuid);
        return positionRepository.countPositionWithUuid(uuid) != 0;
    }
}
