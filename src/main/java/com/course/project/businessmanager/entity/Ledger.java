package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.entity.enums.Bookkeeping;
import com.course.project.businessmanager.entity.enums.ProcurementType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@NamedQuery(
        name = "findBestProduct",
        query = "select l from Ledger l " +
                "join l.building b " +
                "where b.name =:buildingName and l.bookkeeping = 'INCOME' " +
                "group by l.id " +
                "order by sum(l.price)"
)
@NamedQuery(
        name = "findByProcurementType",
        query = "select sum(l.price) from Ledger l " +
                "join l.building b " +
                "where b.name =:buildingName and l.bookkeeping =:procurement "
)

@NamedQuery(
        name = "findExpensesName",
        query = "select distinct l.procurementType, sum(l.price) from Ledger l " +
                "join l.building b " +
                "where b.name =: buildingName and l.bookkeeping = 'EXPENSES'" +
                "group by l.procurementType " +
                "order by sum(l.price)"
)
@NamedQuery(
        name = "findExpensesPrice",
        query = "select sum(l.price) from Ledger l " +
                "join l.building b " +
                "where b.name =: buildingName and l.bookkeeping = 'EXPENSES' " +
                "group by l.procurementType " +
                "order by sum(l.price)"
)
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


    private Long quantity;

    @Enumerated(EnumType.STRING)
    private Bookkeeping bookkeeping;

    @Enumerated(EnumType.STRING)
    private ProcurementType procurementType;

    @Column(name = "due_time")
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm", lenient = OptBoolean.FALSE, timezone = "GMT+3")
    private LocalDateTime dueTime;


    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

}
