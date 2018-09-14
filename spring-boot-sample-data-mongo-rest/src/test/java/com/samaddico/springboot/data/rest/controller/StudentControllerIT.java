/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samaddico.springboot.data.rest.controller;

import com.google.gson.Gson;
import com.samaddico.springboot.data.rest.model.Student;
import com.samaddico.springboot.data.rest.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author addico
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerIT {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private Student student;

    @Autowired
    private StudentService studentService;

    @Before
    public void setUp() {
        studentService.deleteAll();//empty the collection
        student = new Student("Danny", "Computer Science");
        studentService.addStudent(student);
        student = new Student("Harry", "Software Engineering");
        studentService.addStudent(student);
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testSave() throws Exception {
        Gson gson = new Gson();
        Student newStudent = new Student("Diane", "Computer Networks");

        this.mvc.perform(post("/save")
                .content(gson.toJson(newStudent))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo(newStudent.getName())))
                .andExpect(jsonPath("programme", equalTo(newStudent.getProgramme())));
    }

    @Test
    public void testList() throws Exception {
        this.mvc.perform(get(
                "/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.length()", equalTo(2)));
    }

    @Test
    public void testFind() throws Exception {
        this.mvc.perform(get(
                "/find/Danny"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("name", equalTo("Danny")))
                .andExpect(jsonPath("programme", equalTo("Computer Science")));
    }

    @Test
    public void testDelete() throws Exception {
        this.mvc.perform(get(
                "/delete/" + student.getId()))
                .andExpect(status().isOk());
        List<Student> students = studentService.getAllStudents();
        Assert.assertEquals(1, students.size());
    }
}
