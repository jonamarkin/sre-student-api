package com.markin.studentmanagement.infrastructure.adapters.input.rest;

import com.markin.studentmanagement.application.port.input.StudentUseCase;
import com.markin.studentmanagement.infrastructure.adapters.output.persistence.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class StudentRestAdapter {
    private final StudentUseCase studentUseCase;
    private final StudentMapper studentMapper;
}


