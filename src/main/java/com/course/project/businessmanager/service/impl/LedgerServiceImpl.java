package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Equipment;
import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.entity.Warehouse;
import com.course.project.businessmanager.entity.enums.ProcurementType;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.repository.LedgerRepository;
import com.course.project.businessmanager.service.EquipmentService;
import com.course.project.businessmanager.service.LedgerService;
import com.course.project.businessmanager.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class LedgerServiceImpl implements LedgerService {

    private final LedgerRepository ledgerRepository;

    private final WarehouseService warehouseService;
    private final EquipmentService equipmentService;

    @Autowired
    public LedgerServiceImpl(LedgerRepository ledgerRepository, WarehouseService warehouseService, EquipmentService equipmentService) {
        this.ledgerRepository = ledgerRepository;
        this.warehouseService = warehouseService;
        this.equipmentService = equipmentService;
    }

    /**
     * Method gets information from Repository for the particular ledger with id parameter
     *
     * @param id Identity number of the ledger
     * @return Ledger entity
     */
    @Override
    public Ledger getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return ledgerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Ledger.class, "id", id.toString()));
    }

    /**
     * Method gets information about all ledgers from Repository
     *
     * @return List of all ledgers
     */
    @Override
    public List<Ledger> getAll() {
        log.info("In getAll()");
        return ledgerRepository.getAll();
    }

    /**
     * Method saves a new ledger to Repository
     *
     * @param object Ledger entity with info to be saved
     * @return saved Ledger entity
     */
    @Override
    public Ledger save(Ledger object) {
        object.setDueTime(LocalDateTime.now());
        log.info("In save(entity = [{}]", object);
        transferLedger(object);
        return ledgerRepository.save(object);
    }

    @Override
    public List<Ledger> getBestProduct(String name){
        return ledgerRepository.findBestProduct(name).orElse(Collections.emptyList());
    }

    @Override
    public List<Long> getExpensesPrice(String name){
        return ledgerRepository.findExpensesPrice(name).orElse(Collections.emptyList());
    }

    @Override
    public List<ProcurementType> getExpensesName(String name){
        return ledgerRepository.findExpensesName(name).orElse(Collections.emptyList());
    }

    @Override
    public Long getTotalProcurement(String name, String buildingName){
        return ledgerRepository.findTotalByProcurement(name, buildingName).
                orElse(null);
    }

    /**
     * Method updates information for an existing ledger in  Repository
     *
     * @param object Ledger entity with info to be updated
     * @return updated Ledger entity
     */
    @Override
    public Ledger update(Ledger object) {
        log.info("In update(entity = [{}]", object);
        transferLedger(object);
        return ledgerRepository.update(object);
    }

    /**
     * Method deletes an existing ledger from Repository
     *
     * @param object Ledger entity to be deleted
     * @return deleted Ledger entity
     */
    @Override
    public Ledger delete(Ledger object) {
        log.info("In delete(entity = [{}])", object);
        return ledgerRepository.delete(object);
    }

    /**
     * Method transfer ledger either to warehouse or equipment
     *
     * @param object
     */
    private void transferLedger(Ledger object) {
        if (object.getProcurementType() == ProcurementType.WAREHOUSE) {
            warehouseService.save(new Warehouse(
                            null,
                            object.getName(),
                            object.getQuantity(),
                            object.getUnitOfMeasurement(),
                            object.getBuilding()),
                    object.getBookkeeping());
        } else if (object.getProcurementType() == ProcurementType.EQUIPMENT) {
            equipmentService.save(new Equipment(
                            null,
                            object.getName(),
                            null,
                            object.getQuantity(),
                            object.getPrice(),
                            object.getBuilding()),
                    object.getBookkeeping());
        }
    }
}
