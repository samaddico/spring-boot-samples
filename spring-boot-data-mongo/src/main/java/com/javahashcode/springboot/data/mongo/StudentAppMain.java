/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahashcode.springboot.data.mongo;

import com.javahashcode.springboot.data.mongo.model.Student;
import com.javahashcode.springboot.data.mongo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author addico
 */
@SpringBootApplication
public class StudentAppMain implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StudentAppMain.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        studentRepository.deleteAll();

        studentRepository.save(new Student("Nat", "Computer Science"));
        studentRepository.save(new Student("Steve", "Software Engineering"));
        studentRepository.save(new Student("Ann", "Computer Science"));

        System.out.println("Students found with findAll():");
        System.out.println("-------------------------------");
        studentRepository.findAll().stream().forEach((student) -> {
            System.out.println(student);
        });
        System.out.println();

        System.out.println("Search for Ann : ");
        System.out.println("--------------------------------");
        System.out.println(studentRepository.findByName("Ann"));
        
        System.out.println();

        System.out.println("Search for all Computer Science Student:");
        System.out.println("--------------------------------");
        studentRepository.findByProgramme("Computer Science").stream().forEach((Student) -> {
            System.out.println(Student);
        });
    }
}
