package com.markin.studentmanagement.infrastructure.adapters.input.rest;

import com.markin.studentmanagement.application.port.input.StudentUseCase;
import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.infrastructure.adapters.input.rest.dto.StudentRequestDto;
import com.markin.studentmanagement.infrastructure.adapters.input.rest.dto.StudentResponseDto;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentRestAdapter {
    private final StudentUseCase studentUseCase;
    private final StudentMapper studentMapper;

    @GetMapping(value = "/students")
    public ResponseEntity<StudentResponseDto> getAllStudents() {
        List<Student> students = studentUseCase.getAllStudents();
        return new ResponseEntity<>(StudentResponseDto.builder()
                .data(students)
                .message("Students retrieved successfully")
                .status("success")
                .build(), HttpStatus.OK);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(Long id){
        Student student = studentUseCase.getStudentById(id);
        return new ResponseEntity<>(StudentResponseDto.builder()
                .data(student)
                .message("Student successfully retrieved")
                .status("success")
                .build(), HttpStatus.OK);
    }

    @PutMapping(value = "/students")
    public ResponseEntity<StudentResponseDto> updateSExistingStudent(StudentRequestDto studentRequestDto, Long id){
        Student existingStudent = studentUseCase.getStudentById(id);

        if(studentRequestDto.getFirstName()!=null){
            existingStudent.setFirstName(studentRequestDto.getFirstName());
        }

        if(studentRequestDto.getLastName()!=null){
            existingStudent.setLastName(studentRequestDto.getLastName());
        }

        if(studentRequestDto.getDateOfBirth()!=null){
            existingStudent.setDateOfBirth(LocalDateTime.parse(studentRequestDto.getDateOfBirth()));
        }

        Student updatedStudent = studentUseCase.updateStudent(id, existingStudent);
        return new ResponseEntity<>(StudentResponseDto.builder()
                .data(updatedStudent)
                .message("Student information updated")
                .status("success")
                .build(), HttpStatus.OK);
    }
}


