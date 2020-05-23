package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class LedgerRepositoryImpl extends BasicRepositoryImpl<Ledger, UUID> implements LedgerRepository {
}
