package com.gmail.arthurstrokov.system.repository;

import com.gmail.arthurstrokov.system.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository for saving data in DB
 * No need implementation, just one interface, and you have CRUD, thanks Spring Data!
 *
 * @author Arthur Strokov
 */
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findByStudentNumber(long studentNumber);

    Student findByEmail(String email);

    List<Student> findAllByOrderByGpaDesc();
}
