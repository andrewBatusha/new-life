package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Warehouse;
import com.course.project.businessmanager.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class WarehouseRepositoryImpl extends BasicRepositoryImpl<Warehouse, UUID> implements WarehouseRepository{

    /**
     * The method used for getting number of warehouses with name from database
     *
     * @param name String name used to find Warehouse
     * @return Long number of records with name
     */
    @Override
    public Long countWarehouseWithName(String name) {
        log.info("In countWarehouseWithName(name = [{}])", name);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Warehouse w WHERE w.name = :name")
                .setParameter("name", name).getSingleResult();
    }

    /**
     * The method used for getting Warehouse by name from database
     *
     * @param name String name used to find warehouse by it
     * @return Warehouse
     */
    @Override
    public Optional<Warehouse> findByName(String name) {
        log.info("Enter into findByEmail method with name:{}", name);
        TypedQuery<Warehouse> query = getSession().createNamedQuery("findName", Warehouse.class).setMaxResults(1);
        query.setParameter("name", name);
        List<Warehouse> warehouses = query.getResultList();
        if (warehouses.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }


}
