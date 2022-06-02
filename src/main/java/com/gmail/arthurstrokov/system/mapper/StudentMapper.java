package com.gmail.arthurstrokov.system.mapper;

import com.gmail.arthurstrokov.system.dto.StudentDTO;
import com.gmail.arthurstrokov.system.model.Student;
import org.mapstruct.Mapper;

/**
 * Code generator interface that greatly simplifies
 * the implementation of mappings between Java bean types
 *
 * @author Arthur Strokov
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentDTO studentDTO);

    StudentDTO toDto(Student student);
}
