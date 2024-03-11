package com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper;

import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.infrastructure.adapters.input.rest.dto.StudentRequestDto;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.entity.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StudentMapper {

    @Autowired
    private ModelMapper modelMapper;


    public Student toStudent(StudentEntity studentEntity) {
        return modelMapper.map(studentEntity, Student.class);
    }

    public StudentEntity toStudentEntity(Student student) {
        return modelMapper.map(student, StudentEntity.class);
    }


    public  Student toStudentFromDto(StudentRequestDto studentRequestDto) {
        if (studentRequestDto == null) {
            return null;
        }

        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setDateOfBirth(LocalDateTime.parse(studentRequestDto.getDateOfBirth()));

        return student;
    }


}
