package com.samaddico.error.handling.controller;

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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DummyControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private DummyController dummyController;


    @Before
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void when_NameIsPassed_Then_ReturnName() throws Exception {

        this.mvc.perform(get("/hello?name=Samuel")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", equalTo("operation successful")))
                .andExpect(jsonPath("data", containsString("Samuel")));
    }


    @Test
    public void when_NameIsNotPassed_Then_ReturnError() throws Exception {

        this.mvc.perform(get("/hello?name=")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", equalTo("Parameter 'name' required")));
    }
}
