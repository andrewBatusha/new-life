package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Business;
import com.course.project.businessmanager.repository.BusinessRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class BusinessRepositoryImpl extends BasicRepositoryImpl<Business, UUID> implements BusinessRepository {

    /**
     * Method gets information about all businesses from DB
     *
     * @return List of all businesses with ASCII sorting by title
     */
    @Override
    public List<Business> getAll() {
        log.info("In getAll()");
        Session session = getSession();
        return session.createQuery("from Business ORDER BY name ASC")
                .getResultList();
    }

    /**
     * The method used for getting number of businesses with name from database
     *
     * @param name String name used to find business
     * @return Long number of records with name
     */
    @Override
    public Long countBusinessWithName(String name) {
        log.info("In countBusinessWithName(name = [{}])", name);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Business b WHERE b.name = :name")
                .setParameter("name", name).getSingleResult();
    }
}
