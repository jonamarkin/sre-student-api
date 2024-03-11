package com.markin.studentmanagement.infrastructure.adapters.output.persistence;

import com.markin.studentmanagement.application.port.output.StudentOutputPort;
import com.markin.studentmanagement.domain.exception.StudentNotFoundException;
import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.entity.StudentEntity;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper.StudentMapper;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class StudentPersistenceAdapter implements StudentOutputPort {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    @Override
    public Student save(Student student) {
        StudentEntity studentEntity = studentMapper.toStudentEntity(student);
        studentRepository.save(studentEntity);
        log.info("Student with ID" + student.getId() + "saved");
        return studentMapper.toStudent(studentEntity);
    }

    @Override
    public Optional<Student> findById(Long id) {
        Optional<StudentEntity> studentEntity =  studentRepository.findById(id);

        if(studentEntity.isEmpty()){
            log.error(String.format("Student with ID %s not found", id));
            throw new StudentNotFoundException(String.format("Student with ID %s not found", id));
        }

        Student student = studentMapper.toStudent(studentEntity.get());
        log.info(String.format("Student with ID %s retrieved", id));
        return Optional.of(student);
    }

    @Override
    public void delete(Long id) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);

        if(studentEntity.isEmpty()){
            log.error(String.format("Student with ID %s not found", id));
            throw new StudentNotFoundException(String.format("Student with ID %s not found", id));
        }

        studentRepository.delete(studentEntity.get());

    }

    @Override
    public List<Student> findAll() {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();

        for(StudentEntity studentEntity: studentEntityList){
            studentList.add(studentMapper.toStudent(studentEntity));
        }

        return studentList;
    }
}
