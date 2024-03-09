package com.markin.studentmanagement.application.ports.input;

import com.markin.studentmanagement.domain.exception.StudentNotFoundException;
import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentUseCase {
    Student addStudent(Student student);
    List<Student> getAllStudents();

    Student getStudentById(UUID studentId);

    void updateStudent(UUID studentId, Student student) throws StudentNotFoundException;

    void deleteStudent(UUID studentId);
}
