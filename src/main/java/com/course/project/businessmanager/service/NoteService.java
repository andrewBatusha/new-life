package com.course.project.businessmanager.service;

import com.course.project.businessmanager.entity.Note;

import java.util.UUID;

public interface NoteService extends BasicService<Note, UUID> {
    boolean isNoteExistsWithTitle(String title, String email);
}
