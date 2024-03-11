package com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper;

import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.entity.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentMapper {

    @Autowired
    private ModelMapper modelMapper;


    public Student toStudent(StudentEntity studentEntity){
        return modelMapper.map(studentEntity, Student.class);
    }

    public StudentEntity toStudentEntity(Student student){
        return modelMapper.map(student, StudentEntity.class);
    }
}
