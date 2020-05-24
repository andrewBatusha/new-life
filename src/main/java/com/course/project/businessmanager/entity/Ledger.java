package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.entity.enums.ProcurementType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "ledgers")
public class Ledger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(length = 35, nullable = false)
    private String name;


    private int quantity;

    @Enumerated(EnumType.STRING)
    private Bookkeeping bookkeeping;

    @Enumerated(EnumType.STRING)
    private ProcurementType procurementType;


    @Column(name = "due_time")
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dueTime;


    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buildings_id", nullable = false)
    private Building building;

}
