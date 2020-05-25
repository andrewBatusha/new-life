package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.entity.enums.NoteStatus;
import com.course.project.businessmanager.entity.enums.Priority;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NoteDTO {

    private UUID id;

    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters long")
    private String title;

    @NotBlank(message = "Note body cannot be empty")
    @Size(min = 2, max = 250, message = "Name must be between 2 and 250 characters long")
    private String body;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm", lenient = OptBoolean.FALSE, timezone = "GMT+3")
    private LocalDateTime dueTime;

    private Priority priority;

    private NoteStatus noteStatus;

    @JsonIgnore
    private User user;
}
