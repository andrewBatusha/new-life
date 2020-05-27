package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.entity.enums.TimeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;


@NamedQuery(
        name = "findEmployeesByBoss",
        query = "select e from Employee e " +
                "join e.position p where p.employee.email = :email"
)
@NamedQuery(
        name = "findBoss",
        query = "select e from Employee e " +
                "join e.position p " +
                "join e.building b where p.employee.id is null and b.id = :id"
)
@NamedQuery(
        name = "findByEmail",
        query = "select e from Employee e " +
                "join e.building b " +
                "where b.name =:buildingName and e.email =:email"
)
@Entity
@NoArgsConstructor
@Data
@ToString(exclude={"position", "building"})
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 35, nullable = false)
    private String name;

    @Column(length = 35, nullable = false)
    private String surname;

    @Column(length = 35, nullable = false)
    private String patronymic;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;

    @Enumerated(EnumType.STRING)
    private TimeType timeType;

    @Column(unique = true, length = 40)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

}
