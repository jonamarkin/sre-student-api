package com.markin.studentmanagement.application.ports;

import com.markin.studentmanagement.application.ports.input.StudentUseCase;
import com.markin.studentmanagement.application.ports.output.StudentRepository;
import com.markin.studentmanagement.domain.exception.StudentNotFoundException;
import com.markin.studentmanagement.domain.model.Student;

import java.util.List;
import java.util.UUID;

public class StudentServiceImpl implements StudentUseCase {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
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
        return studentRepository.findById(studentId);
    }

    @Override
    public void updateStudent(UUID studentId, Student student) throws StudentNotFoundException {
        Student existingStudent = studentRepository.findById(studentId);

        if(existingStudent!=null){
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
