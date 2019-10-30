package com.unosquare.cvgenerator.controller;

import com.flextrade.jfixture.JFixture;
import com.unosquare.cvgenerator.AbstractWebIntegrationTest;
import com.unosquare.cvgenerator.dao.UserRepository;
import com.unosquare.cvgenerator.model.entity.User;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserControllerIntegrationTest extends AbstractWebIntegrationTest {

    @Autowired
    private UserRepository repository;

    private User user1;
    private User user2;

    @Before
    public void setUp() {
        JFixture fixture = new JFixture();
        user1 = repository.save(fixture.create(User.class));
        user2 = repository.save(fixture.create(User.class));
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void getUser_whenValidIdPassed_returnsUser() {
        RestAssured.when()
                .get("/users/" + user1.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(user1.getId()))
                .body("firstName", equalTo(user1.getFirstName()))
                .body("lastName", equalTo(user1.getLastName()));
    }

    @Test
    public void getUser_whenInvalidIdPassed_returnNoContentStatusCode() {
        int guaranteedInvalidId = user1.getId() + user2.getId();
        RestAssured.when()
                .get("/users/" + guaranteedInvalidId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void getUsers_whenDataAvailable_returnsUserList() {
        RestAssured.when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].id", equalTo(user1.getId()))
                .body("[0].firstName", equalTo(user1.getFirstName()))
                .body("[0].lastName", equalTo(user1.getLastName()))
                .body("[1].id", equalTo(user2.getId()))
                .body("[1].firstName", equalTo(user2.getFirstName()))
                .body("[1].lastName", equalTo(user2.getLastName()));
    }

    @Test
    public void getUsers_whenNoData_returnNoContentStatusCode() {
        repository.deleteAll();
        RestAssured.when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
