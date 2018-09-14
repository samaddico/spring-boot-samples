/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samaddico.springboot.data.rest.service;

import com.samaddico.springboot.data.rest.model.Student;
import java.util.List;

/**
 *
 * @author addico
 */
public interface StudentService {

    public List<Student> getAllStudents();

    public Student addStudent(Student student);

    public void removeStudent(String id);

    public Student getStudentByName(String name);

    public void deleteAll();

}
