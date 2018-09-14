/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samaddico.springboot.data.rest.controller;

import com.samaddico.springboot.data.rest.model.Student;
import com.samaddico.springboot.data.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author addico
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Student save(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Student> list() {
        return studentService.getAllStudents();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/delete/{id}")
    public String delete(@PathVariable String id) {
        studentService.removeStudent(id);
        return "student deleted";
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/find/{name}")
    public Student find(@PathVariable String name) {
        return studentService.getStudentByName(name);
    }

}
