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
     * The method used for getting number of notes with title from database
     *
     * @param title title of Note
     * @param email Note owners mail
     * @return Long number of records with title
     */
    @Override
    public Long countNoteWithName(String title, String email) {
        log.info("In countNoteWithName(title = [{}], email = [{}])", title, email);
        return (Long) getSession().createQuery
                ("SELECT count (*) FROM Note n WHERE n.title = :title " +
                        "join n.user u with u.email= :email")
                .setParameter("title", title)
                .setParameter("email",email).getSingleResult();
    }
}
