package com.markin.studentmanagement.application.port.input;

import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentUseCase {
    Student addStudent(Student student);
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
