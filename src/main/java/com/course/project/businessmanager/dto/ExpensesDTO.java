package com.course.project.businessmanager.dto;

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
    private List<String> name = Collections.emptyList();
    private List<Long> price =Collections.emptyList();
}
