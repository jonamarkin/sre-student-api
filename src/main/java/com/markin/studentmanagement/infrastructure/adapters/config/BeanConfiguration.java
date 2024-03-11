package com.markin.studentmanagement.infrastructure.adapters.config;

import com.markin.studentmanagement.domain.service.StudentService;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.StudentPersistenceAdapter;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper.StudentMapper;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    StudentMapper studentMapper(){
        return new StudentMapper();
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    StudentPersistenceAdapter studentPersistenceAdapter(StudentRepository studentRepository, StudentMapper studentMapper){
        return new StudentPersistenceAdapter(studentRepository, studentMapper);
    }

    @Bean
    StudentService studentService(StudentPersistenceAdapter studentPersistenceAdapter){
        return new StudentService(studentPersistenceAdapter);
    }

}
