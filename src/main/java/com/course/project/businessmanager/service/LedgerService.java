package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.Ledger;

import java.util.List;
import java.util.UUID;

public interface LedgerService extends BasicService<Ledger, UUID>{
    List<Ledger> getBestProduct(String name);

    public Long getTotalProcurement(String name, String buildingName);
}