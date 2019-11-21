package com.unosquare.cvgenerator.controller;

import com.flextrade.jfixture.JFixture;
import com.unosquare.cvgenerator.AbstractWebIntegrationTest;
import com.unosquare.cvgenerator.dao.TemplateRepository;
import com.unosquare.cvgenerator.model.entity.TemplateDetail;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;

public class TemplateControllerIntegrationTest extends AbstractWebIntegrationTest {

    @Autowired
    private TemplateRepository repository;

    private TemplateDetail templateDetail1;
    private TemplateDetail templateDetail2;


    @Before
    public void setUp() {
        JFixture fixture = new JFixture();
        templateDetail1 = repository.save(fixture.create(TemplateDetail.class));
        templateDetail2 = repository.save(fixture.create(TemplateDetail.class));
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void getTemplate_whenValidIdPassed_returnsTemplate() {
        RestAssured.when()
                .get("/templates/" + templateDetail1.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(templateDetail1.getId()))
                .body("templateName", equalTo(templateDetail1.getTemplateName()));
    }

    @Test
    public void getTemplate_whenInvalidIdPassed_returnNoContentStatusCode() {
        int guaranteedInvalidId = templateDetail1.getId() + templateDetail2.getId();
        RestAssured.when()
                .get("/templates/" + guaranteedInvalidId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void getTemplates_whenDataAvailable_returnsTemplateList() {
        RestAssured.when()
                .get("/templates")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0].id", equalTo(templateDetail1.getId()))
                .body("[0].templateName", equalTo(templateDetail1.getTemplateName()))
                .body("[1].id", equalTo(templateDetail2.getId()))
                .body("[1].templateName", equalTo(templateDetail2.getTemplateName()));
    }

    @Test
    public void getTemplates_whenNoData_returnNoContentStatusCode() {
        repository.deleteAll();
        RestAssured.when()
                .get("/templates")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
