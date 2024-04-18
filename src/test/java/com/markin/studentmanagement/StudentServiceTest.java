package com.markin.studentmanagement;

import com.markin.studentmanagement.application.port.output.StudentOutputPort;
import com.markin.studentmanagement.domain.exception.StudentNotFoundException;
import com.markin.studentmanagement.domain.model.Student;
import com.markin.studentmanagement.domain.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@SpringBootTest

@SpringBootTest(classes = StudentServiceTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
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

    @Test
    public void testGetAllStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student());
        studentList.add(new Student());
        when(studentOutputPort.findAll()).thenReturn(studentList);
        assertEquals(studentList, studentService.getAllStudents());
    }

    @Test
    public void testGetStudentById(){
        Long id = 1L;
        Student student = new Student();
        when(studentOutputPort.findById(id)).thenReturn(Optional.of(student));
        assertEquals(student, studentService.getStudentById(id));
    }

    @Test(expected = StudentNotFoundException.class)
    public void testGetStudentByIdNotFound(){
        Long id = 1L;
        when(studentOutputPort.findById(id)).thenReturn(Optional.empty());
        studentService.getStudentById(id);
    }

    @Test
    public void testUpdateStudent() {
        Long id = 1L;
        Student existingStudent = new Student();
        Student updatedStudent = new Student();
        when(studentOutputPort.findById(id)).thenReturn(Optional.of(existingStudent));
        when(studentOutputPort.save(updatedStudent)).thenReturn(updatedStudent);
        assertEquals(updatedStudent, studentService.updateStudent(id, updatedStudent));
    }

    @Test(expected = StudentNotFoundException.class)
    public void testUpdateStudentNotFound() {
        Long id = 1L;
        Student student = new Student();
        when(studentOutputPort.findById(id)).thenReturn(Optional.empty());
        studentService.updateStudent(id, student);
    }

    @Test
    public void testDeleteStudent() {
        Long id = 1L;
        studentService.deleteStudent(id);
        verify(studentOutputPort, times(1)).delete(id);
    }
}
