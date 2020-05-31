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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class LedgerDTO {

    private UUID id;

    @NotBlank
    @Size(min = 2, max = 35)
    private String name;

    @Positive
    private Long quantity;

    private Bookkeeping bookkeeping;

    private ProcurementType procurementType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm", lenient = OptBoolean.FALSE, timezone = "GMT+3")
    private LocalDateTime dueTime;

    @NotBlank
    @Size(min = 2, max = 10)
    private String unitOfMeasurement;

    @PositiveOrZero
    private Long price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Building building;
}
