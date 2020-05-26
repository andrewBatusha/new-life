package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Business;

import java.util.UUID;

public interface BusinessRepository extends BasicRepository<Business, UUID> {
    Long countBusinessWithId(UUID uuid);
}
