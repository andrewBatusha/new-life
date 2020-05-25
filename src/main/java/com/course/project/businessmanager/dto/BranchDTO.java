package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Employee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BranchDTO {
    private List<Employee> employees = new ArrayList<>();

    private Employee boss;
}
