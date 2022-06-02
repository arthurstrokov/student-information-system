package com.gmail.arthurstrokov.system.controller;

import com.gmail.arthurstrokov.system.dto.StudentDTO;
import com.gmail.arthurstrokov.system.mapper.StudentMapper;
import com.gmail.arthurstrokov.system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller
 *
 * @author Arthur Strokov
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentRestController {
    private final StudentService studentService;
    private final StudentMapper mapper;

    @GetMapping(value = "/")
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/byStudentNumber/{studentNumber}")
    public StudentDTO getStudentByStudentNumber(@PathVariable("studentNumber") Long studentNumber) {
        return mapper.toDto(studentService.findByStudentNumber(studentNumber));
    }

    @GetMapping(value = "/byEmail/{email}")
    public StudentDTO getStudentByEmail(@PathVariable("email") String email) {
        return mapper.toDto(studentService.findByEmail(email));
    }

    @GetMapping(value = "/orderByGpa")
    public List<StudentDTO> findAllByOrderByGpaDesc() {
        return studentService.findAllByOrderByGpaDesc().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateStudent(@RequestBody StudentDTO studentDTO) {
        studentService.saveOrUpdateStudent(mapper.toEntity(studentDTO));
        return new ResponseEntity("Student added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{studentNumber}")
    public ResponseEntity<?> deleteStudentByStudentNumber(@PathVariable long studentNumber) {
        studentService.deleteStudentById(studentService.findByStudentNumber(studentNumber).getId());
        return new ResponseEntity("Student deleted successfully", HttpStatus.OK);
    }
}
