package com.samaddico.springboot.caching.controller;


import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
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
public class RedisControllerTest {

    @Autowired
    private RedisController redisController;


    @Before
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(redisController);

        Map<String,Object> payload = new HashMap();
        payload.put("id","Wyi9fh8Mn80p");
        payload.put("title","DevOps");
        payload.put("author","Samuel Addico");

        given()
                .log()
                .all().contentType("application/json")
                .body(payload)
                .when()
                .post("/put")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true));

    }

    @Test
    public void when_ObjectIsPutInCache_Then_ReturnObject(){
        Map<String,Object> payload = new HashMap();
        payload.put("id","XZi32fh8Mn0p");
        payload.put("title","DevOps");
        payload.put("author","Samuel Addico");

        given()
                .log()
                .all().contentType("application/json")
                .body(payload)
                .when()
                .post("/put")
                .then()
                .statusCode(200)
                .body("data.id", Matchers.is((String) payload.get("id")));
    }

    @Test
    public void when_SessionIdIsParsed_Then_ReturnObjectFromCache(){

        String bookId = "Wyi9fh8Mn80p";

        given()
                .log()
                .all().contentType("application/json")
                .when()
                .get("/get?id=" + bookId)
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true))
                .body("data.id", Matchers.is(bookId));

    }

    @Test
    public void when_ObjectEvicted_Then_RemoveFromCache(){
        String bookId = "Wyi9fh8Mn80p";

        given()
                .log()
                .all().contentType("application/json")
                .when()
                .get("/evict?id=" + bookId)
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true));
    }

}