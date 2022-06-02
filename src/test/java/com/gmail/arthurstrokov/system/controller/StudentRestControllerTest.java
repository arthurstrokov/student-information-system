package com.gmail.arthurstrokov.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.arthurstrokov.system.mapper.StudentMapper;
import com.gmail.arthurstrokov.system.model.Student;
import com.gmail.arthurstrokov.system.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
class StudentRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private StudentService studentService;
    @MockBean
    private StudentMapper mapper;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Student arthur;
    private Student avistate;
    private final Long arthurStudentNumber = 23L;

    @BeforeEach
    void setUp() {
        arthur = new Student();
        arthur.setId("aBc123");
        arthur.setName("arthur");
        arthur.setEmail("arthurstrokov@gmail.com");
        arthur.setStudentNumber(arthurStudentNumber);
        arthur.setCourseList(Arrays.asList("Math", "Science"));
        arthur.setGpa(3.37f);

        avistate = new Student();
        avistate.setId("dEf345");
        avistate.setName("avistate");
        avistate.setEmail("avistate@yandex.ru");
        long avistateStudentNumber = 91L;
        avistate.setStudentNumber(avistateStudentNumber);
        avistate.setCourseList(Arrays.asList("Turkish", "German"));
        avistate.setGpa(3.58f);
    }

    @Test
    void getAllStudents() throws Exception {
        given(studentService.findAll()).willReturn(Collections.singletonList(arthur));

        mvc.perform(get("/students/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
//                .andExpect(jsonPath("$[0].name", is(arthur.getName())));
    }

    @Test
    void getStudentByStudentNumber() throws Exception {
        given(studentService.findByStudentNumber(arthurStudentNumber)).willReturn(arthur);

        mvc.perform(get("/students/byStudentNumber/{studentNumber}", arthurStudentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name", is(arthur.getName())));
    }

    @Test
    void getStudentByEmail() {
    }

    @Test
    void findAllByOrderByGpaDesc() throws Exception {
        given(studentService.findAllByOrderByGpaDesc()).willReturn(Arrays.asList(avistate, arthur));

        mvc.perform(get("/students/orderByGpa/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
//                .andExpect(jsonPath("$[0].name", is(avistate.getName())));
    }

    @Test
    void saveOrUpdateStudent() throws Exception {
        given(studentService.saveOrUpdateStudent(any(Student.class))).willReturn(avistate);

        String jsonString = objectMapper.writeValueAsString(avistate);

        mvc.perform(post("/students/save/")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStudentByStudentNumber() throws Exception {
        given(studentService.findByStudentNumber(arthurStudentNumber)).willReturn(arthur);
        Mockito.doNothing().when(studentService).deleteStudentById(any(String.class));

        mvc.perform(delete("/students/delete/{studentNumber}", arthurStudentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}