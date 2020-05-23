package com.course.project.businessmanager.entity;

import com.course.project.businessmanager.entity.enums.NoteType;
import com.course.project.businessmanager.entity.enums.Priority;
import lombok.Data;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "notes")
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters long")
    @Column(length = 100)
    private String title;

    @NotEmpty(message = "Note body cannot be empty")
    @Size(min = 2, max = 250, message = "Name must be between 2 and 250 characters long")
    @Column(length = 250, nullable = false)
    private String body;

    @NotNull(message = "Due time cannot be empty")
    @Column(name = "due_time")
    private Timestamp dueTime;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private NoteType noteType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

}
