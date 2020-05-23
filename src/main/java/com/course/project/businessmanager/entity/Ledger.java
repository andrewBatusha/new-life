package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.entity.enums.ProcurementType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "ledgers")
public class Ledger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 35 characters long")
    @Column(length = 35, nullable = false)
    private String name;

    @Min(1)
    private int quantity;

    @Enumerated(EnumType.STRING)
    private Bookkeeping bookkeeping;

    @Enumerated(EnumType.STRING)
    private ProcurementType procurementType;

    @NotNull(message = "Due time cannot be empty")
    @Column(name = "due_time")
    private LocalDateTime dueTime = LocalDateTime.now();
    @Size(min = 2, max = 10, message = "unit of measurement must be between 2 and 10 characters long")
    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @Min(1)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildings_id", nullable = false)
    private Building building;

}
