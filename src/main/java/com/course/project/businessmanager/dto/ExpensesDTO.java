package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.enums.ProcurementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesDTO {
    private ProcurementType name;
    private Long price;
}
