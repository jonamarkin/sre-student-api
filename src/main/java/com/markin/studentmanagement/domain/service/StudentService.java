package com.markin.studentmanagement.domain.service;

import com.markin.studentmanagement.application.ports.input.StudentUseCase;
import com.markin.studentmanagement.application.ports.output.StudentRepository;
import com.markin.studentmanagement.domain.exception.StudentNotFoundException;
import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentService implements StudentUseCase {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(String.format("Student with ID %s not found", studentId)));
    }

    @Override
    public void updateStudent(UUID studentId, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(studentId);

        if(existingStudent.isPresent()){
            student.setStudentId(studentId);
            studentRepository.save(student);
        }else{
            throw new StudentNotFoundException(String.format("Student with ID %s not found", studentId));
        }
    }

    @Override
    public void deleteStudent(UUID studentId) {
        studentRepository.delete(studentId);
    }
}
