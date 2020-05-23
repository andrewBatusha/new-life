package com.course.project.businessmanager.service;


import java.io.Serializable;
import java.util.List;

interface BasicService<T extends Serializable, I extends Serializable> {

    T getById(I id);

    List<T> getAll();

    T update(T object);

    T save(T object);

    T delete(T object);
}

