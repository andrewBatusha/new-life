package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Ledger;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LedgerRepository extends BasicRepository<Ledger, UUID> {
    List<Map<String, Long>> findExpenses();
}
