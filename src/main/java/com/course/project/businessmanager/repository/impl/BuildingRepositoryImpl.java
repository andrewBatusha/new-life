package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.repository.BuildingRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class BuildingRepositoryImpl extends BasicRepositoryImpl<Building, UUID> implements BuildingRepository {

    /**
     * Method gets information about all buildings from DB
     *
     * @return List of all buildings with ASCII sorting by name
     */
    @Override
    public List<Building> getAll() {
        log.info("In getAll()");
        Session session = getSession();
        return session.createQuery("from Business ORDER BY name ASC")
                .getResultList();
    }

    /**
     * The method used for getting number of buidings with name from database
     *
     * @param name String name used to find building
     * @return Long number of records with name
     */
    @Override
    public Long countBuildingWithName(String name) {
        log.info("In countBuildingWithName(name = [{}])", name);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Building b WHERE b.name = :name")
                .setParameter("name", name).getSingleResult();
    }

    /**
     * Modified update method, which merge entity before updating it
     *
     * @param entity user is going to be updated
     * @return User
     */
    @Override
    public Building update(Building entity) {
        log.info("Enter into update method with entity:{}", entity);
        entity = (Building) getSession().merge(entity);
        getSession().update(entity);
        return entity;
    }
    /**
     * The method used for getting Building id by user email from database
     *
     * @param email String email used to find Building by it
     * @return UUID
     */
    @Override
    public Optional<Building> findBuildingByEmail(String email) {
        log.info("Enter into findBuildingIdByEmail  with email:{}", email);
        TypedQuery<Building> query = getSession().createNamedQuery("findBuildingByEmail", Building.class).setMaxResults(1);
        query.setParameter("email", email);
        List<Building> buildings = query.getResultList();
        if (buildings.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }

}