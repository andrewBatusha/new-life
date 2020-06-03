package com.course.project.businessmanager.dto;

import com.course.project.businessmanager.entity.User;
import com.course.project.businessmanager.entity.enums.NoteStatus;
import com.course.project.businessmanager.entity.enums.Priority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NoteDTO {

    private UUID id;

    @NotBlank(message = "Note title cannot be empty")
    @Size(min = 2, max = 100, message = "Note title must be between 2 and 100 characters long")
    private String title;

    @NotBlank(message = "Note body cannot be empty")
    @Size(min = 2, max = 250, message = "Note body must be between 2 and 250 characters long")
    private String body;

    private Priority priority;

    private NoteStatus noteStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
