package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Business;

import java.util.UUID;

public interface BusinessService extends BasicService<Business, UUID> {
    boolean isBusinessExistsWithTitle(String title);

    Business addUserToBusiness(Business object, String email);
}
