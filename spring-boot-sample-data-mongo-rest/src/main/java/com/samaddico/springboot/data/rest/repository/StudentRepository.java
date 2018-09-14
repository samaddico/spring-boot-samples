/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.samaddico.springboot.data.rest.repository;

import com.samaddico.springboot.data.rest.model.Student;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author addico
 */
public interface StudentRepository extends MongoRepository<Student,String> {
    
    public List<Student> findByProgramme(String programme);
    
    public Student findByName(String name);
}
