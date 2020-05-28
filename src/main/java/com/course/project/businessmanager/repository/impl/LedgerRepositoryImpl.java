package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class LedgerRepositoryImpl extends BasicRepositoryImpl<Ledger, UUID> implements LedgerRepository {
    @Override
    public List<Ledger> findExpenses() {
        return (List<Ledger>) getSession().createQuery
                ("select l.name, sum(l.price) from Ledger l " +
        "where l.bookkeeping = 'EXPENSES' group by l.name, l.price").list();
    }
}
