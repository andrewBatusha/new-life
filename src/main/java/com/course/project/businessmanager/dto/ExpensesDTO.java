package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.enums.ProcurementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesDTO {
    private ProcurementType name;

    @Positive(message = "quantity must be positive")
    @Min(value = 1, message = "the lowest value for price field is 1")
    private Long price;
}
