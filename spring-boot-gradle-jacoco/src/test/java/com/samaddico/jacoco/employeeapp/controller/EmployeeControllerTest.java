package com.samaddico.jacoco.employeeapp.controller;


import com.samaddico.jacoco.employeeapp.model.Employee;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.samaddico.jacoco.employeeapp.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {


    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;

    @Before
    public void setup(){
        employee = new Employee();
        employee.setEmail("teddy@blog.io");
        employee.setFirstName("Yvonne");
        employee.setLastName("Nelson");
        employee.setRole("Snr Producer");
        employee.setDepartment("Production Unit");
        employeeService.addNew(employee);

        RestAssuredMockMvc.standaloneSetup(employeeController);
    }

    @Test
    public void whenEmployeeDetailIsParsed_ThenSaveDetail() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("firstName", "Dave");
        payload.put("lastName", "Reeds");
        payload.put("email", "somedude@devs.com");
        payload.put("role", "Software Engineer");
        payload.put("department", "Engineering");

        given()
                .log()
                .all().contentType("application/json")
                .body(payload)
                .when()
                .post("/api/add")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true))
                .body("message", Matchers.is("SUCCESS"))
                .body("data.firstName", Matchers.is((String) payload.get("firstName")))
                .body("data.lastName", Matchers.is((String) payload.get("lastName")));
    }

    @Test
    public void when_a_parameter_is_not_parsed_fail() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("firstName", "Dave");
        payload.put("lastName", "Reeds");
        payload.put("email", "somedude@devs.com");
        payload.put("role", "Software Engineer");

        given()
                .log()
                .all().contentType("application/json")
                .body(payload)
                .when()
                .post("/api/add")
                .then()
                .statusCode(400);
    }

    @Test
    public void list_all_employees() {
        given()
                .log()
                .all().contentType("application/json")
                .when()
                .get("/api/all")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true));
    }
}
