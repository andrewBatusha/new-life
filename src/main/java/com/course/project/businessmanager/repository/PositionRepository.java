package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Position;

import java.util.UUID;

public interface PositionRepository extends BasicRepository<Position, UUID> {
    Long countPositionWithUuid(String uuid);
}
