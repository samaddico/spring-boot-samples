package com.samaddico.springboot.controller;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.samaddico.springboot.form.ItemForm;
import com.samaddico.springboot.model.Item;
import com.samaddico.springboot.service.ItemService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemControllerTest {

    @Autowired
    private ItemController itemController;

    @Autowired
    private ItemService itemService;

    private Item item;

    @Before
    public void setup(){

        itemService.deleteAll();

        item = new Item();
        item.setName("Polo Shirt");
        item.setPrice(new BigDecimal("15.5"));
        item.setCategory("Fashion");
        item.setQuantity(3);
        item = itemService.addItem(item);

        RestAssuredMockMvc.standaloneSetup(itemController);
    }

    @Test
    public void whenItemIsParsed_ThenSaveItem_AndReturnItem() {

        ItemForm payload = new ItemForm();
        payload.setName("Iphone X");
        payload.setPrice(new BigDecimal("369"));
        payload.setQuantity(34);
        payload.setCategory("Electronics");

        given()
                .log()
                .all().contentType("application/json")
                .body(payload)
                .when()
                .post("/items/api/add")
                .then()
                .statusCode(200)
                .body("success", Matchers.is(true))
                .body("message", Matchers.is("Item Created"))
                .body("data.name", Matchers.is(payload.getName()))
                .body("data.quantity", Matchers.is(payload.getQuantity()));
    }

    @Test
    public void test_deleteItem() {

        given()
                .log()
                .all().contentType("application/json")
                .when()
                .get("/items/api/remove/" + item.getId())
                .then()
                .statusCode(200)
                .body("success", Matchers.is(true));
    }

    @Test
    public void test_findByName() {
        given()
                .log()
                .all().contentType("application/json")
                .when()
                .get("/items/api/find?name=" + item.getName())
                .then()
                .statusCode(200)
                .body("success", Matchers.is(true))
                .body("data.name", Matchers.is(item.getName()))
                .body("data.quantity", Matchers.is(item.getQuantity()))
                .body("message", Matchers.is("Item found"));
    }

    @Test
    public void test_findByName_NotFound() {
        given()
                .log()
                .all().contentType("application/json")
                .when()
                .get("/items/api/find?name=CK Shirt")
                .then()
                .statusCode(200)
                .body("success", Matchers.is(true))
                .body("message", Matchers.is("Item not found"));
    }

    @Test
    public void test_findAll() {
        given()
                .log()
                .all().contentType("application/json")
                .when()
                .get("/items/api/all")
                .then()
                .statusCode(200)
                .body("success", Matchers.is(true))
                .body("data", Matchers.hasSize(1));
    }
}
