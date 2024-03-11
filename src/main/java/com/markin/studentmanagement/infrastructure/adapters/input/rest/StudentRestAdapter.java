package com.markin.studentmanagement.infrastructure.adapters.input.rest;

import com.markin.studentmanagement.application.port.input.StudentUseCase;
import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.infrastructure.adapters.input.rest.dto.StudentRequestDto;
import com.markin.studentmanagement.infrastructure.adapters.input.rest.dto.StudentResponseDto;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class StudentRestAdapter {
    private final StudentUseCase studentUseCase;
    private final StudentMapper studentMapper;

    @GetMapping(value = "/health")
    public ResponseEntity<StudentResponseDto> healthCheck(){
        log.info("Student service is up!");
        return new ResponseEntity<>(StudentResponseDto.builder()
                .message("Student service is up!")
                .status("success")
                .build(), HttpStatus.OK);
    }

    @PostMapping(value="/students")
    public ResponseEntity<StudentResponseDto> addNewStudent(@RequestBody StudentRequestDto studentRequestDto){

    }

    @GetMapping(value = "/students")
    public ResponseEntity<StudentResponseDto> getAllStudents() {
        log.info("Fetching all students");
        List<Student> students = studentUseCase.getAllStudents();
        return new ResponseEntity<>(StudentResponseDto.builder()
                .data(students)
                .message("Students retrieved successfully")
                .status("success")
                .build(), HttpStatus.OK);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(Long id){
        log.info("Fetching student with ID {}", id);
        Student student = studentUseCase.getStudentById(id);
        return new ResponseEntity<>(StudentResponseDto.builder()
                .data(student)
                .message("Student successfully retrieved")
                .status("success")
                .build(), HttpStatus.OK);
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<StudentResponseDto> updateSExistingStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable Long id){
        log.info("Updating student with ID {}", id);
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

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<StudentResponseDto> deleteStudent(@PathVariable Long id){
        log.info("Deleting a student with ID {}", id);
        studentUseCase.deleteStudent(id);
        return new ResponseEntity<>(StudentResponseDto.builder()
                .message("Student deleted successfully")
                .status("success")
                .build(), HttpStatus.OK);
    }
}


