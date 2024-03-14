package com.markin.studentmanagement;

import com.markin.studentmanagement.application.port.output.StudentOutputPort;
import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.domain.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {

    private StudentOutputPort studentOutputPort;
    private StudentService studentService;

    @Before
    public void setUp(){
        studentOutputPort = mock(StudentOutputPort.class);
        studentService = new StudentService(studentOutputPort);
    }

    @Test
    public void testAddStudent(){
        Student student = new Student();
        when(studentOutputPort.save(student)).thenReturn(student);
        assertEquals(student, studentService.addStudent(student));
    }
}
