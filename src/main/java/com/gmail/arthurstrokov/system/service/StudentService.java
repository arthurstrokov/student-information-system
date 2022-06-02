package com.gmail.arthurstrokov.system.service;

import com.gmail.arthurstrokov.system.model.Student;

import java.util.List;

/**
 * Service that the student controller will call
 *
 * @author Arthur Strokov
 */
public interface StudentService {

    List<Student> findAll();

    Student findByStudentNumber(long studentNumber);

    Student findByEmail(String email);

    List<Student> findAllByOrderByGpaDesc();

    Student saveOrUpdateStudent(Student student);

    void deleteStudentById(String id);
}
