package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Position;
import com.course.project.businessmanager.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class PositionRepositoryImpl extends BasicRepositoryImpl<Position, UUID> implements PositionRepository {

    /**
     * The method used for getting number of positions with uuid from database
     *
     * @param uuid String uuid used to find Warehouse
     * @return Long number of records with uuid
     */
    @Override
    public Long countPositionWithUuid(String uuid) {
        log.info("In countPositionWithUuid(uuid = [{}])", uuid);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Position p WHERE p.id = :uuid")
                .setParameter("uuid", uuid).getSingleResult();
    }
}