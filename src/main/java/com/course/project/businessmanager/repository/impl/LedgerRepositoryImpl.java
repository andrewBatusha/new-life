package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.entity.enums.ProcurementType;
import com.course.project.businessmanager.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class LedgerRepositoryImpl extends BasicRepositoryImpl<Ledger, UUID> implements LedgerRepository {
    @Override
   public Optional<List<Ledger>> findBestProduct(String buildingName){
       TypedQuery<Ledger> query = getSession().createNamedQuery("findBestProduct", Ledger.class).setMaxResults(5);
       query.setParameter("buildingName", buildingName);
       List<Ledger> resultList = query.getResultList();
       if (resultList.isEmpty()) {
           return Optional.empty();
       }
       return Optional.of(query.getResultList());
    }

    @Override
    public Optional<Long> findTotalByProcurement(String buildingName, String procurementType){
        TypedQuery<Long> query = getSession().createNamedQuery("findByProcurementType", Long.class).setMaxResults(1);
        query.setParameter("buildingName", buildingName);
        query.setParameter("procurement", Bookkeeping.valueOf(procurementType));
        List<Long> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList().get(0));
    }

    @Override
    public Optional<List<Long>> findExpensesPrice(String buildingName){
        TypedQuery<Long> query = getSession().createNamedQuery("findExpensesPrice", Long.class);
        query.setParameter("buildingName", buildingName);
        List<Long> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList());
    }
    @Override
    public Optional<List<String>> findExpensesName(String buildingName){
        TypedQuery<String> query = getSession().createNamedQuery("findExpensesName", String.class);
        query.setParameter("buildingName", buildingName);
        List<String> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(query.getResultList());
    }
}
