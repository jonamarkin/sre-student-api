package com.markin.studentmanagement.domain.service;

import com.markin.studentmanagement.application.port.input.StudentUseCase;
import com.markin.studentmanagement.application.port.output.StudentOutputPort;
import com.markin.studentmanagement.domain.exception.StudentNotFoundException;
import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentService implements StudentUseCase {
    private final StudentOutputPort studentRepository;

    public StudentService(StudentOutputPort studentRepository) {
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
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException(String.format("Student with ID %s not found", id)));
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isPresent()){
            student.setId(id);
            studentRepository.save(student);
        }else{
            throw new StudentNotFoundException(String.format("Student with ID %s not found", id));
        }
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }
}
