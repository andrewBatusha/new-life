package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Position;

import java.util.UUID;

public interface PositionService extends BasicService<Position, UUID> {
    boolean isPositionExistsWithUuid(String uuid);
}
