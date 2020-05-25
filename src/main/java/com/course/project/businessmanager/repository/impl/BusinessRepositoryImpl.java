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
     * The method used for getting number of businesses with uuid from database
     *
     * @param uuid String uuid used to find business
     * @return Long number of records with uuid
     */
    @Override
    public Long countBusinessWithId(String uuid) {
        log.info("In countBusinessWithName(uuid = [{}])", uuid);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Business b WHERE b.id = :uuid")
                .setParameter("uuid", uuid).getSingleResult();
    }
}
