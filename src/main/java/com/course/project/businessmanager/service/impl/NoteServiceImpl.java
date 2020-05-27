package com.course.project.businessmanager.service.impl;

import com.course.project.businessmanager.entity.Note;
import com.course.project.businessmanager.exception.EntityNotFoundException;
import com.course.project.businessmanager.repository.NoteRepository;
import com.course.project.businessmanager.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Method gets information from Repository for the note with id parameter
     *
     * @param id Identity number of the note
     * @return Note entity
     */
    @Override
    public Note getById(UUID id) {
        log.info("In getById(id = [{}])", id);
        return noteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Note.class, "id", id.toString()));
    }

    /**
     * Method gets information about all notes from Repository
     *
     * @return List of all notes
     */
    @Override
    public List<Note> getAll() {
        log.info("In getAll()");
        return noteRepository.getAll();
    }

    /**
     * Method updates information for an existing note in  Repository
     *
     * @param object Note entity with info to be updated
     * @return updated Note entity
     */
    @Override
    public Note update(Note object) {
        log.info("In update(entity = [{}]", object);
        if (isNoteExistsWithTitle(object.getTitle(), object.getUser().getEmail())) {
            return noteRepository.update(object);
        } else {
            throw new EntityNotFoundException(Note.class, "title", String.valueOf(object.getTitle()));
        }
    }

    /**
     * Method saves new note to Repository
     *
     * @param object Note entity with info to be saved
     * @return saved Note entity
     */
    @Override
    public Note save(Note object) {
        log.info("In save(entity = [{}]", object);
        return noteRepository.save(object);
    }


    /**
     * Method deletes an existing note from Repository
     *
     * @param object Note entity to be deleted
     * @return deleted Note entity
     */
    @Override
    public Note delete(Note object) {
        log.info("In delete(entity = [{}])", object);
        return noteRepository.delete(object);
    }

    /**
     * Method finds if Note with title already exists
     *
     * @param title
     * @return true if Note with such title already exist
     */
    @Override
    public boolean isNoteExistsWithTitle(String title, String email) {
        log.info("In isNoteExistsWithTitle(title = [{}], email = [{}])", title, email);
        return noteRepository.countNoteWithName(title, email) != 0;
    }

}
