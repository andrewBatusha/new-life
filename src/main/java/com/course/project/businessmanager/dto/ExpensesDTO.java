package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.enums.ProcurementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesDTO {
    private List<ProcurementType> name = Collections.emptyList();
    private List<Long> price =Collections.emptyList();
}
