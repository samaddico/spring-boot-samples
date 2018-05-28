/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahashcode.springboot.data.rest.service;

import com.javahashcode.springboot.data.rest.model.Student;
import com.javahashcode.springboot.data.rest.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author addico
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Ignore
public class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @Before
    public void before() {
        student = new Student("Danny", "Computer Science");
    }

    @Test
    public void testAddStudent() {

        Mockito.when(studentRepository
                .save(student))
                .thenReturn(student);

        Student actualStudent = studentService.addStudent(student);
        Assert.assertEquals(student, actualStudent);
    }

    @Test
    public void testGetAllStudents() {
        List<Student> expectedList = new ArrayList<>();
        expectedList.add(student);

        Mockito.when(studentRepository
                .findAll())
                .thenReturn(expectedList);

        List<Student> actualList = studentService.getAllStudents();
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void testGetStudentByName() {
        Mockito.when(studentRepository
                .findByName(Matchers.anyString()))
                .thenReturn(student);

        Student actual = studentService.getStudentByName("Danny");
        Assert.assertEquals(student, actual);
    }

}
