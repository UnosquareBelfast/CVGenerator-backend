package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.AbstractWebIntegrationTest;
import com.unosquare.cvgenerator.dao.HelloRepository;
import com.unosquare.cvgenerator.model.entity.Greeting;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.containsString;


public class HelloControllerIntegrationTest extends AbstractWebIntegrationTest {

    @Autowired
    HelloRepository repository;

    @Before
    public void setUp() {
        Greeting greeting = new Greeting(1, "Hello World!");
        repository.save(greeting);
    }

    @Test
    public void testSayHello() {
        RestAssured.when()
                .get("/hello/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("Hello World!"));
    }
}