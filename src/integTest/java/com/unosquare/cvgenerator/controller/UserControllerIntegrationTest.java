package com.unosquare.cvgenerator.controller;

import com.unosquare.cvgenerator.AbstractWebIntegrationTest;
import com.unosquare.cvgenerator.dao.UserRepository;
import com.unosquare.cvgenerator.model.entity.User;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserControllerIntegrationTest extends AbstractWebIntegrationTest {

    @Autowired
    UserRepository repository;

    @Before
    public void setUp() {
        User user1 = new User(1, "Bobby", "Firmino", "bobby@yahoo.com");
        User user2 = new User(2, "Phil", "Jones", "p.jones@hotmail.com");
        repository.save(user1);
        repository.save(user2);
    }

    @Test
    public void testGetUser() {
        RestAssured.when()
                .get("/users/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(1))
                .body("firstName", equalTo("Bobby"))
                .body("lastName", equalTo("Firmino"));
    }

    @Test
    public void testGetUsers() {
        RestAssured.when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].id", equalTo(1))
                .body("[0].firstName", equalTo("Bobby"))
                .body("[0].lastName", equalTo("Firmino"))
                .body("[1].id", equalTo(2))
                .body("[1].firstName", equalTo("Phil"))
                .body("[1].lastName", equalTo("Jones"));
    }

}
