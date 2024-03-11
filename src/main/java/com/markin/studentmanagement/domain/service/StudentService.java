package com.markin.studentmanagement.domain.service;

import com.markin.studentmanagement.application.port.input.StudentUseCase;
import com.markin.studentmanagement.application.port.output.StudentOutputPort;
import com.markin.studentmanagement.domain.exception.StudentNotFoundException;
import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class StudentService implements StudentUseCase {
    private final StudentOutputPort studentOutputPort;

    public StudentService(StudentOutputPort studentOutputPort) {
        this.studentOutputPort = studentOutputPort;
    }

    @Override
    public Student addStudent(Student student) {
        return studentOutputPort.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentOutputPort.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentOutputPort.findById(id).orElseThrow(
                () -> new StudentNotFoundException(String.format("Student with ID %s not found", id)));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> existingStudent = studentOutputPort.findById(id);

        if(existingStudent.isPresent()){
            student.setId(id);
            studentOutputPort.save(student);
        }else{
            throw new StudentNotFoundException(String.format("Student with ID %s not found", id));
        }

        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        studentOutputPort.delete(id);
    }
}
