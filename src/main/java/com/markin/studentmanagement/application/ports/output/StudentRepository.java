package com.markin.studentmanagement.application.ports.output;

import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(UUID studentId);
    void delete(UUID studentId);
    List<Student> findAll();
}
