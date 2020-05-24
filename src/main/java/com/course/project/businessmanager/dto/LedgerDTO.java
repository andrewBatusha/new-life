package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.Building;
import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.entity.enums.ProcurementType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class LedgerDTO {

    private UUID id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    private String name;

    @Min(1)
    private int quantity;

    private Bookkeeping bookkeeping;

    private ProcurementType procurementType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm", lenient = OptBoolean.FALSE)
    private LocalDateTime dueTime;

    @Size(min = 2, max = 10, message = "unit of measurement must be between 2 and 10 characters long")
    private String unitOfMeasurement;

    @Min(1)
    private int price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;
}
