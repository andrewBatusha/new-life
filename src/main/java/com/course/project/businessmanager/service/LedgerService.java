package com.course.project.businessmanager.service;

import com.course.project.businessmanager.dto.ExpensesDTO;
import com.course.project.businessmanager.entity.Ledger;

import java.util.List;
import java.util.UUID;

public interface LedgerService extends BasicService<Ledger, UUID>{
    List<Ledger> getBestProduct(String name);

    Long getTotalProcurement(String name, String buildingName);
    List<ExpensesDTO> getExpensesName(String name);
}