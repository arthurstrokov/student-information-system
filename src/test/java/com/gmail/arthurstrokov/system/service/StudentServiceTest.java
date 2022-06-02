package com.gmail.arthurstrokov.system.service;

import com.gmail.arthurstrokov.system.model.Student;
import com.gmail.arthurstrokov.system.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @MockBean
    private StudentRepository studentRepository;
    private Student arthur;
    private Student avistate;
    private final Long arthurStudentNumber = 23L;
    private final String avistateEmail = "avistate@yandex.ru";
    private final List<Student> students = new ArrayList<>();

    @BeforeEach
    void setUp() {
        arthur = new Student();
        arthur.setId("aBc123");
        arthur.setName("arthur");
        String arthurEmail = "arthurstrokov@gmail.com";
        arthur.setEmail(arthurEmail);
        arthur.setStudentNumber(arthurStudentNumber);
        arthur.setCourseList(Arrays.asList("Math", "Science"));
        arthur.setGpa(3.37f);

        avistate = new Student();
        avistate.setId("dEf345");
        avistate.setName("avistate");
        avistate.setEmail(avistateEmail);
        long avistateStudentNumber = 91L;
        avistate.setStudentNumber(avistateStudentNumber);
        avistate.setCourseList(Arrays.asList("Turkish", "German"));
        avistate.setGpa(3.58f);

        students.add(arthur);
        students.add(avistate);
    }

    @Test
    void findAll() {
        Mockito.when(studentRepository.findAll()).thenReturn(students);
        List<Student> foundStudents = studentService.findAll();
        assertNotNull(foundStudents);
        assertEquals(2, foundStudents.size());
    }

    @Test
    void findByStudentNumber() {
        Mockito.when(studentRepository.findByStudentNumber(arthurStudentNumber)).thenReturn(arthur);
        Student found = studentService.findByStudentNumber(arthurStudentNumber);
        assertNotNull(found);
        assertEquals(arthur.getName(), found.getName());
        assertEquals(arthur.getId(), found.getId());
    }

    @Test
    void findByEmail() {
        Mockito.when(studentRepository.findByEmail(avistateEmail)).thenReturn(avistate);
        Student found = studentService.findByEmail(avistateEmail);
        assertNotNull(found);
        assertEquals(avistate.getName(), found.getName());
        assertEquals(avistate.getId(), found.getId());
    }

    @Test
    void findAllByOrderByGpaDesc() {
        Mockito.when(studentRepository.findAllByOrderByGpaDesc())
                .thenReturn(students.stream().sorted(
                        Comparator.comparing(Student::getGpa).reversed()).collect(Collectors.toList()));
        List<Student> foundStudents = studentService.findAllByOrderByGpaDesc();

        assertNotNull(foundStudents);
        assertEquals(2, foundStudents.size());
        assertEquals(avistate.getName(), foundStudents.get(0).getName());
        assertEquals(avistate.getId(), foundStudents.get(0).getId());
    }

    @Test
    void saveOrUpdateStudent() {
        Mockito.when(studentRepository.save(arthur)).thenReturn(arthur);
        Student found = studentService.saveOrUpdateStudent(arthur);
        assertNotNull(found);
        assertEquals(arthur.getName(), found.getName());
        assertEquals(arthur.getId(), found.getId());
    }

    @Test
    void deleteStudentById() {
        studentService.deleteStudentById(arthur.getId());
        Mockito.verify(studentRepository, Mockito.times(1))
                .deleteById(arthur.getId());
    }
}
