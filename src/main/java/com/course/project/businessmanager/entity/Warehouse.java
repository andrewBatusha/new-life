package com.course.project.businessmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@NamedQuery(
        name = "findName",
        query = "select w from Warehouse w where w.name= :name"
)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "warehouses")
public class Warehouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 35, nullable = false)
    private String name;

    private Long quantity;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
}
