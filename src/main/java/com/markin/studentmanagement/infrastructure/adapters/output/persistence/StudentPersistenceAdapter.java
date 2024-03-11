package com.markin.studentmanagement.infrastructure.adapters.output.persistence;

import com.markin.studentmanagement.application.port.output.StudentOutputPort;
import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.entity.StudentEntity;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper.StudentMapper;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentOutputPort {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    @Override
    public Student save(Student student) {
        StudentEntity studentEntity = studentMapper.toStudentEntity(student);
        studentRepository.save(studentEntity);
        return studentMapper.toStudent(studentEntity);
    }

    @Override
    public Optional<Student> findById(Long id) {
        Optional<StudentEntity> studentEntity =  studentRepository.findById(id);

        if(studentEntity.isEmpty()){
            return Optional.empty();
        }

        Student student = studentMapper.toStudent(studentEntity.get());
        return Optional.of(student);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
