package com.gmail.arthurstrokov.system.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO Model for present data in view
 *
 * @author Arthur Strokov
 */
@Data
public class StudentDTO {
    private String id;
    private String name;
    private long studentNumber;
    private String email;
    private List<String> courseList;
    private float gpa;
}
