package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.entity.Warehouse;
import com.course.project.businessmanager.repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class EquipmentRepositoryImpl extends BasicRepositoryImpl<Equipment, UUID> implements EquipmentRepository {

    /**
     * The method used for getting number of ledgers with some name from database
     *
     * @param name String name used to find Ledger
     * @return Long number of records with name
     */
    @Override
    public Long countEquipmentsWithName(String name) {
        log.info("In countLedgersWithName(name = [{}])", name);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Equipment e WHERE e.name = :name")
                .setParameter("name", name).getSingleResult();
    }

    /**
     * The method used for getting Warehouse by equipmentName from database
     *
     * @param equipmentName String equipmentName used to find warehouse by it
     * @return Warehouse
     */
    @Override
    public Optional<Equipment> findByName(String equipmentName, String buildingName ) {
        log.info("Enter into findByEmail method with equipmentName:{}", equipmentName);
        TypedQuery<Equipment> query = getSession().createNamedQuery("findEquipmentName", Equipment.class).setMaxResults(1);
        query.setParameter("equipmentName", equipmentName);
        query.setParameter("buildingName", buildingName);
        List<Equipment> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }
}
