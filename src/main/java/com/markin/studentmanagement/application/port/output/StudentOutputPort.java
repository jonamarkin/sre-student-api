package com.markin.studentmanagement.application.port.output;

import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentOutputPort {
    Student save(Student student);
    Optional<Student> findById(Long id);
    void delete(Long id);
    List<Student> findAll();
}
