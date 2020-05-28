package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@Slf4j
public class LedgerRepositoryImpl extends BasicRepositoryImpl<Ledger, UUID> implements LedgerRepository {
    @Override
    public List<Map<String, Long>> findExpenses() {
        return (List<Map<String, Long>>) getSession().createQuery
                ("select new map(l.name, sum(l.price)) from Ledger l " +
        "where l.bookkeeping = 'EXPENSES' group by l.name, l.price").getResultList();
    }
}
