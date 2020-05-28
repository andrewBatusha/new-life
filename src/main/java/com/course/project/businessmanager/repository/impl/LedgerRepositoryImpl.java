package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Ledger;
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
}
