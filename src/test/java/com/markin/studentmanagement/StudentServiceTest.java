package com.markin.studentmanagement;

import com.markin.studentmanagement.application.port.output.StudentOutputPort;
import com.markin.studentmanagement.domain.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class StudentServiceTest {

    private StudentOutputPort studentOutputPort;
    private StudentService studentService;

    @Before
    public void setUp(){
        studentOutputPort = mock(StudentOutputPort.class);
        studentService = new StudentService(studentOutputPort);
    }

//    @Test
//    public void testAddStudent()
}
