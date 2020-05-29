package com.course.project.businessmanager.controller;

import com.course.project.businessmanager.dto.BuildingDTO;
import com.course.project.businessmanager.dto.ExpensesDTO;
import com.course.project.businessmanager.dto.LedgerDTO;
import com.course.project.businessmanager.entity.Ledger;
import com.course.project.businessmanager.mapper.LedgerMapper;
import com.course.project.businessmanager.service.LedgerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Ledger API")
@RequestMapping("/ledgers")
@Slf4j
public class LedgerController {

    private final LedgerService ledgerService;
    private final LedgerMapper ledgerMapper;

    @Autowired
    public LedgerController(LedgerService ledgerService, LedgerMapper ledgerMapper) {
        this.ledgerService = ledgerService;
        this.ledgerMapper = ledgerMapper;
    }

    @GetMapping
    @ApiOperation(value = "Get the list of all ledgers")
    public ResponseEntity<List<LedgerDTO>> list() {
        log.info("Enter into list of LedgerController");
        return ResponseEntity.ok().body(ledgerMapper.convertToDtoList(ledgerService.getAll()));
    }

    @GetMapping("/best")
    public ResponseEntity<List<LedgerDTO>> bestProductList(@RequestParam String name) {
        log.info("Enter into list of LedgerController");
        return ResponseEntity.ok().body(ledgerMapper.convertToDtoList(ledgerService.getBestProduct(name)));
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpensesDTO>> expensesList(@RequestParam String name) {
        log.info("Enter into expensesList of LedgerController");
        return ResponseEntity.ok().body(ledgerService.getExpensesName(name));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> bestProductList(@RequestParam String procurementName,
        @RequestParam String buildingName){
        log.info("Enter into list of LedgerController");
        return ResponseEntity.ok().body(ledgerService.getTotalProcurement(procurementName, buildingName));
    }


    @PostMapping
    @ApiOperation(value = "Create new ledger")
    public ResponseEntity<LedgerDTO> save(@Valid @RequestBody LedgerDTO ledgerDTO) {
        log.info("Enter into save of LedgerController with ledgerDTO: {}", ledgerDTO);
        Ledger ledger = ledgerService.save(ledgerMapper.convertToEntity(ledgerDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ledgerMapper.convertToDto(ledger));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get ledger info by id")
    public ResponseEntity<LedgerDTO> get(@PathVariable("id") UUID id){
        log.info("In get(id = [{}])", id);
        Ledger ledger = ledgerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ledgerMapper.convertToDto(ledger));
    }

    @PutMapping
    @ApiOperation(value = "Update existing ledger by id")
    public ResponseEntity<LedgerDTO> update(@Valid @RequestBody LedgerDTO ledgerDTO) {
        log.info("In update (ledgerDTO = [{}])", ledgerDTO);
        Ledger ledger = ledgerService.update(ledgerMapper.convertToEntity(ledgerDTO));
        return ResponseEntity.status(HttpStatus.OK).body(ledgerMapper.convertToDto(ledger));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete ledger by id")
    public ResponseEntity delete(@PathVariable("id") UUID id){
        log.info("In delete (id =[{}]", id);
        Ledger ledger = ledgerService.getById(id);
        ledgerService.delete(ledger);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
