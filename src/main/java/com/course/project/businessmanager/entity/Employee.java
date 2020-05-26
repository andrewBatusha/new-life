package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.entity.enums.TimeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
                "join e.position p with p.employee.id is null " +
                "join e.building b with b.id = :id"
)
@Entity
@NoArgsConstructor
@Data
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
