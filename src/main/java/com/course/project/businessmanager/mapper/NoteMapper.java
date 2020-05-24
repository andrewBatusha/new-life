package com.course.project.businessmanager.mapper;

import com.course.project.businessmanager.dto.NoteDTO;
import com.course.project.businessmanager.entity.Note;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    Note convertToEntity(NoteDTO dto);

    NoteDTO convertToDto(Note entity);

    List<NoteDTO> convertToDtoList(List<Note> notes);

}
