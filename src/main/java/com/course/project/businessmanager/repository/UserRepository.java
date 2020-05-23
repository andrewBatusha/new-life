package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BasicRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
}
