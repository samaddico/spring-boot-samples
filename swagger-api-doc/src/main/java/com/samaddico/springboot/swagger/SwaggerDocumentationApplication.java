package com.samaddico.springboot.swagger;

import com.samaddico.springboot.swagger.model.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SwaggerDocumentationApplication {

    private static final List<Contact> contacts =  new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(SwaggerDocumentationApplication.class, args);
    }


    public static List<Contact> getContacts(){

        Contact contact1 = new Contact();
        contact1.setFirstName("Andraina");
        contact1.setLastName("Kelly");
        contact1.setTelephone("233240594933");
        contact1.setEmail("kelly@tech.com");
        contact1.setAddress("Accra");

        Contact contact2 = new Contact();
        contact2.setFirstName("Joe");
        contact2.setLastName("Gardtner");
        contact2.setTelephone("233241594553");
        contact2.setEmail("joe@tech.com");
        contact2.setAddress("SA");

        Contact contact3 = new Contact();
        contact3.setFirstName("Samuel");
        contact3.setLastName("Addico");
        contact3.setTelephone("23321112224");
        contact3.setEmail("kelly@tech.com");
        contact3.setAddress("Kumasi");

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);

        return contacts;
    }
}

