package com.gmail.arthurstrokov.system.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Model for saving data in DB
 * # @Document annotation is used for the same purpose with @Entity annotation in JPA.
 * @author Arthur Strokov
 */
@Data
@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private long studentNumber;
    private String email;
    private List<String> courseList;
    private float gpa;
}
