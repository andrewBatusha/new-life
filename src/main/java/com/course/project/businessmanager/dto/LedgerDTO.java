package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.entity.enums.ProcurementType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
public class LedgerDTO {

    private long id;

    private String name;

    private int quantity;

    private Bookkeeping bookkeeping;

    private ProcurementType procurementType;

    private Timestamp dueTime;

    private String unitOfMeasurement;

    private int price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;
}
