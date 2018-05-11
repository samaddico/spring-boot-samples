/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahashcode.springboot.data.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author addico
 */
@Document(collection = "student")
public class Student {

    @Id
    private String id;
    private String name;
    private String programme;

    public Student(String name, String programme) {
        this.name = name;
        this.programme = programme;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    @Override
    public String toString() {
        return "{" + "id:" + id + ", name:" + name + ", programme:" + programme + '}';
    }
}
