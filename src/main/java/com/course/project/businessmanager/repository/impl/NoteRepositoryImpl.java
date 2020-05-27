package com.course.project.businessmanager.repository.impl;

import com.course.project.businessmanager.entity.Note;
import com.course.project.businessmanager.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class NoteRepositoryImpl extends BasicRepositoryImpl<Note, UUID> implements NoteRepository {

    /**
     * The method used for getting number of notes with name from database
     *
     * @param name title of Note
     * @param email Note owners mail
     * @return Long number of records with name
     */
    @Override
    public Long countNoteWithName(String name, String email) {
        log.info("In countNoteWithName(name = [{}])", name);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Note n WHERE n.name = :name " +
                        "join b.user u where u.email= :email")
                .setParameter("name", name)
                .setParameter("email", email).getSingleResult();
    }
}
