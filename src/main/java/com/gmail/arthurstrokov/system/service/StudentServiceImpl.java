package com.gmail.arthurstrokov.system.service;

import com.gmail.arthurstrokov.system.model.Student;
import com.gmail.arthurstrokov.system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that the student controller will call
 *
 * @author Arthur Strokov
 */
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByStudentNumber(long studentNumber) {
        return studentRepository.findByStudentNumber(studentNumber);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<Student> findAllByOrderByGpaDesc() {
        return studentRepository.findAllByOrderByGpaDesc();
    }

    @Override
    public Student saveOrUpdateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(String id) {
        studentRepository.deleteById(id);
    }
}
