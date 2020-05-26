package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class PositionDTO {

    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    private String name;

    private Employee employee;

    @Positive
    private Long salary;

    @PositiveOrZero
    private Long numberOfSubordinate;
}
