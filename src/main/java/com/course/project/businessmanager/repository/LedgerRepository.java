package com.course.project.businessmanager.repository;

import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.entity.enums.ProcurementType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LedgerRepository extends BasicRepository<Ledger, UUID> {
    Optional<List<Ledger>> findBestProduct(String buildingName);
    Optional<Long> findTotalByProcurement(String buildingName, String procurementType);
    Optional<List<ProcurementType>> findExpensesName(String buildingName);
    Optional<List<Long>> findExpensesPrice(String buildingName);
}
