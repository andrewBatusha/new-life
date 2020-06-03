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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class LedgerDTO {

    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    private String name;

    @Positive(message = "quantity must be positive")
    @Min(value = 1, message = "the lowest value for quantity field is 1")
    private Long quantity;

    private Bookkeeping bookkeeping;

    private ProcurementType procurementType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm", lenient = OptBoolean.FALSE, timezone = "GMT+3")
    private LocalDateTime dueTime;

    @NotBlank(message = "unit of measurement must not be blank")
    @Size(min = 2, max = 10)
    private String unitOfMeasurement;

    @Positive(message = "price must be positive")
    @Min(value = 1, message = "the lowest value for price field is 1")
    private Long price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;
}
