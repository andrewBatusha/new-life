package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Ledger;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LedgerService extends BasicService<Ledger, UUID>{
    List<Map<String, Long>> getLedgerExpenses();
}